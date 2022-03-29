package com.smart_quiz.webservice.app.services.impl;

import com.smart_quiz.webservice.app.models.User;
import com.smart_quiz.webservice.app.models.exception.ApiException;
import com.smart_quiz.webservice.app.models.exception.ResourceNotFoundException;
import com.smart_quiz.webservice.app.models.request.ForgetPasswordRequest;
import com.smart_quiz.webservice.app.models.request.LoginRequest;
import com.smart_quiz.webservice.app.models.request.OtpSignupRequestDto;
import com.smart_quiz.webservice.app.models.request.SignupRequest;
import com.smart_quiz.webservice.app.models.request.UpdatePasswordRequest;
import com.smart_quiz.webservice.app.models.request.UserRequest;
import com.smart_quiz.webservice.app.models.response.JwtResponse;
import com.smart_quiz.webservice.app.models.response.UserDetails;
import com.smart_quiz.webservice.app.models.response.UserResponse;
import com.smart_quiz.webservice.app.services.UserService;
import com.smart_quiz.webservice.global.constants.GlobalErrorConstants;
import com.smart_quiz.webservice.global.enums.AuthProvider;
import com.smart_quiz.webservice.global.enums.AuthRole;
import com.smart_quiz.webservice.global.enums.ErrorPriority;
import com.smart_quiz.webservice.global.enums.UsernameType;
import com.smart_quiz.webservice.infrastructure.entity.UserEntity;
import com.smart_quiz.webservice.infrastructure.repository.UserRepository;
import com.smart_quiz.webservice.security.TokenProvider;
import com.smart_quiz.webservice.security.UserPrincipal;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.modelmapper.ModelMapper;

@Data
@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired(required = true)
    private final UserRepository userRepository;

    @Autowired(required = true)
    private final AuthenticationManager authenticationManager;

    @Autowired(required = true)
    private final TokenProvider tokenProvider;

    @Autowired(required = true)
    private final ModelMapper modelMapper;

    public UserServiceImpl(
            TokenProvider tokenProvider,
            ModelMapper modelMapper,
            AuthenticationManager authenticationManager,
            UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;

    }

    @Override
    public JwtResponse authenticate(LoginRequest loginRequest) {
        try {

            UserEntity userEntity = userRepository.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("Username does not exist."));

            // if
            // (!Arrays.asList(AuthProvider.local).contains(userEntity.getAuthProvider())) {
            // throw new ApiException(
            // "Looks like you're signed up with your " +
            // userEntity.getAuthProvider().name()
            // + " account. Please login via " + userEntity.getAuthProvider().name() + " to
            // continue",
            // HttpStatus.BAD_REQUEST);
            // }

            // if (userEntity.getIsActive() != null && !userEntity.getIsActive()) {
            // throw new ResourceNotFoundException(
            // "This account was previously deleted. To log in to your account again, please
            // reset your password");
            // }

            JwtResponse jwtResponse = generateJwtToken(loginRequest.getUsername(), loginRequest.getPassword());

            return jwtResponse;
        } catch (ApiException ex) {
            throw ex;
        } catch (BadCredentialsException ex) {
            throw ex;
        } catch (Exception ex) {

            throw new ApiException(ErrorPriority.High, ex);
        }
    }

    @Override
    public UserResponse retrieveUser(String userRequest) throws ResourceNotFoundException {
        try {
            boolean isUsername = userRepository.existsByUsername(userRequest);
            boolean isEmail = userRepository.existsByEmail(userRequest);

            if (!isUsername && !isEmail) {
                throw new ResourceNotFoundException(GlobalErrorConstants.USER_ACCOUNT_NOT_EXIST);
            }

            UserEntity userRetrieved = isUsername ? userRepository.findByUsername(userRequest).orElse(null)
                    : userRepository.findByEmail(userRequest).orElse(null);
            return new UserResponse(Objects.requireNonNull(userRetrieved));
        } catch (ApiException ex) {
            throw ex;
        } catch (Exception ex) {

            throw new ApiException(ErrorPriority.High, ex);
        }
    }

    private JwtResponse generateJwtToken(String userName, String password) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userName, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        List<String> roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        UserEntity userEntity = userRepository.findByUsername(userName).orElseGet(UserEntity::new);
        userEntity.setLastLoginStamp(new Date(System.currentTimeMillis()));
        userRepository.save(userEntity);

        return JwtResponse.builder()
                .token(jwt)
                .id(principal.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .username(principal.getUsername())
                .email(principal.getEmail())
                .roles(roles)
                .creationDate(userEntity.getCreationDate())
                .lastLoginTime(userEntity.getLastLoginStamp())
                .build();
    }

    @Override
    public UserResponse registerUser(SignupRequest signupRequest) {
        try {
            UserEntity existingUser = userRepository.findByUsername(signupRequest.getUsername()).orElse(null);
            if (existingUser != null && existingUser.getIsActive() == false) {
                throw new ApiException(GlobalErrorConstants.USER_ACCOUNT_DELETED);
            }
            if (existingUser != null && existingUser.getIsActive() == true) {
                throw new ApiException(GlobalErrorConstants.USER_ACCOUNT_EXISTS);
            }

            if (signupRequest.getEmail() != null) {
                if (userRepository.existsByEmail(signupRequest.getEmail())) {
                    throw new ApiException(GlobalErrorConstants.EMAIL_EXISTS);
                }
            }

            User user = modelMapper.map(signupRequest, User.class);
            user.setPassword(user.getPassword());
            user.setRole(AuthRole.valueOf(signupRequest.getRole().toUpperCase()));
            user.setCreationDate(new Date(System.currentTimeMillis()));
            UserEntity userEntity = modelMapper.map(user, UserEntity.class);
            userEntity.setAuthProvider(AuthProvider.local);
            userEntity.setUsernameType(UsernameType.GENERIC);
            userRepository.save(userEntity);

            user.setId(userEntity.getId());
            user.maskPassword();
            UserResponse userResponse = modelMapper.map(user, UserResponse.class);
            userResponse.setAuthRole(user.getRole().toString().toUpperCase());

            return userResponse;
        } catch (ApiException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, ErrorPriority.Low);
        }
    }

    @Override
    public UserResponse updatePassword(UpdatePasswordRequest passwordRequest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteUser(long userId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sendForgetPasswordOTP(String username) {
        // TODO Auto-generated method stub

    }

    @Override
    public void resetPasswordWithOTP(String userName, ForgetPasswordRequest forgetPasswordRequest) {
        // TODO Auto-generated method stub

    }

    @Override
    public UserEntity checkUserExist(String userName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserEntity checkUserExist(Long userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponse signupWithOTP(OtpSignupRequestDto signupDetail) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserEntity findUserById(Long userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserDetails getUserDetails(Long userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponse getUser(long userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addMobileNumber(long userId, String mobile) {
        // TODO Auto-generated method stub

    }

    @Override
    public void verifyMobileNumber(long userId, String otp) {
        // TODO Auto-generated method stub

    }

    @Override
    public void verifyEmailAddress(long userId, String otp) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sendOtpToUser(long userId, String type) {
        // TODO Auto-generated method stub

    }

    @Override
    public UserResponse updateUser(String loggedInUsername, Long userId, UserRequest userRequest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JwtResponse getAuthorizationDetails(Long userId) {
        // TODO Auto-generated method stub
        return null;
    }

}

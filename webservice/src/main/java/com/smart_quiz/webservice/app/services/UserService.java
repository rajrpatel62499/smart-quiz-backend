package com.smart_quiz.webservice.app.services;

import javax.annotation.ManagedBean;

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
import com.smart_quiz.webservice.global.enums.AuthProvider;
import com.smart_quiz.webservice.global.enums.AuthRole;
import com.smart_quiz.webservice.infrastructure.entity.UserEntity;

import org.springframework.stereotype.Component;

@Component
public interface UserService {
    UserResponse registerUser(SignupRequest signupRequest);

    JwtResponse authenticate(LoginRequest loginRequest);

    UserResponse retrieveUser(String userRequest) throws ResourceNotFoundException;

    UserResponse updatePassword(UpdatePasswordRequest passwordRequest);

    void deleteUser(long userId);

    void sendForgetPasswordOTP(String username);

    void resetPasswordWithOTP(String userName, ForgetPasswordRequest forgetPasswordRequest);

    UserEntity checkUserExist(String userName);

    UserEntity checkUserExist(Long userId);

    UserResponse signupWithOTP(OtpSignupRequestDto signupDetail);

    UserEntity findUserById(Long userId);

    UserDetails getUserDetails(Long userId);

    // PagedJackettLibraryResponse getJacketLibrary(int page, int size, String
    // searchBy, String subject, String className,
    // String exam);

    // PagedDashboardUserResponse getDashboardUsers(String searchBy, String[] sort,
    // int page, int size);

    // PagedUserResponse getUsers(String searchBy, String[] sort, int page, int
    // size);

    UserResponse getUser(long userId);

    void addMobileNumber(long userId, String mobile);

    void verifyMobileNumber(long userId, String otp);

    void verifyEmailAddress(long userId, String otp);

    void sendOtpToUser(long userId, String type);

    UserResponse updateUser(String loggedInUsername, Long userId, UserRequest userRequest);

    JwtResponse getAuthorizationDetails(Long userId);
}

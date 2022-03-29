package com.smart_quiz.webservice.app.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.smart_quiz.webservice.app.models.request.LoginRequest;
import com.smart_quiz.webservice.app.models.request.SignupRequest;
import com.smart_quiz.webservice.app.models.response.JwtResponse;
import com.smart_quiz.webservice.app.models.response.UserResponse;
import com.smart_quiz.webservice.app.services.UserService;

@Slf4j
@RestController
@Validated
public class AuthController {

    private final UserService userService;

    // @Autowired
    // private OtpService otpService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/api/auth/signup")
    @Operation(description = "Sign up api for jackett webservcie", summary = "Sign up api for jackett webservcie")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody SignupRequest signupRequest) {

        UserResponse registeredUser = userService.registerUser(signupRequest);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping(value = "/api/auth/login")
    @Operation(description = "User login api", summary = "User login api")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = userService.authenticate(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @GetMapping(value = "/api/auth/user")
    @Operation(description = "Retrieve user information api with username", summary = "Retrieve user information api with username")
    public ResponseEntity<UserResponse> retrieveUser(
            @RequestParam @NotBlank(message = "Invalid search parameter.") String userRequest) {
        return ResponseEntity.ok(userService.retrieveUser(userRequest));
    }

    // @GetMapping(value = "/api/resource/authorization-details")
    // @PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT') or
    // hasRole('STUDENT_ADMIN') or hasRole('TEACHER')")
    // @PostAuthorize("returnObject.body.id == #principal.getId()")
    // @Operation(description = "Get authorized user authentication details",
    // summary = "Get authorized user authentication details", security = {
    // @SecurityRequirement(name = GlobalConstants.OPEN_API_TOKEN_NAME) })
    // public ResponseEntity<JwtResponse>
    // getAuthorizationDetails(@AuthenticationPrincipal UserPrincipal principal) {
    // return
    // ResponseEntity.ok(userService.getAuthorizationDetails(principal.getId()));
    // }

    // @PutMapping(value = "/api/resource/update-password")
    // @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN') or hasRole('USER') or
    // hasRole('RESOURCE_BANK')")
    // @Operation(description = "Update password", summary = "Update the old
    // password", security = {
    // @SecurityRequirement(name = GlobalConstants.OPEN_API_TOKEN_NAME) })
    // public ResponseEntity<UserResponse> updatePassword(@Valid @RequestBody
    // UpdatePasswordRequest passwordRequest) {
    // return ResponseEntity.ok(userService.updatePassword(passwordRequest));
    // }

    // @PutMapping(value = "/api/resource/update-username")
    // @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN') or hasRole('USER') or
    // hasRole('RESOURCE_BANK')")
    // @Operation(description = "Update username api", summary = "Update username
    // api", security = {
    // @SecurityRequirement(name = GlobalConstants.OPEN_API_TOKEN_NAME) })
    // public ResponseEntity<UserResponse> updateUsername(@Valid @RequestBody
    // UpdateUsernameRequest usernameRequest) {
    // return ResponseEntity.ok(userService.updateUsername(usernameRequest));
    // }

    // @PostMapping(value = "/api/resource/get/jackett-library")
    // @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN') or
    // hasRole('RESOURCE_BANK')")
    // @Operation(description = "Get Jackett library", summary = "Search and Filter
    // Jackett library", security = {
    // @SecurityRequirement(name = GlobalConstants.OPEN_API_TOKEN_NAME) })
    // public ResponseEntity<PagedJackettLibraryResponse>
    // getResourceFolders(@RequestParam(defaultValue = "0") int page,
    // @RequestParam(defaultValue = "10") int size,
    // @RequestParam(name = "searchBy", required = false) String searchBy,
    // @RequestParam(name = "subject", required = false) String subject,
    // @RequestParam(name = "className", required = false) String className,
    // @RequestParam(name = "exam", required = false) String exam) {

    // return ResponseEntity.ok(userService.getJacketLibrary(page, size, searchBy,
    // subject, className, exam));
    // }

    // @PutMapping(value = "/api/auth/forget-password/{username}/otp")
    // @Operation(description = "Send forget password otp api", summary = "Send
    // forget password otp api")
    // public ResponseEntity sendForgetPasswordOTP(@PathVariable("username") String
    // username) {
    // log.info("send-forget-password-otp api call initaited for username: " +
    // username);
    // userService.sendForgetPasswordOTP(username);
    // return ResponseEntity.ok().build();
    // }

    // @PutMapping(value = "/api/auth/forget-password/{username}")
    // @Operation(description = "Reset forgotten password with otp api", summary =
    // "Reset forgotten password with otp api")
    // public ResponseEntity resetPasswordWithOTP(@PathVariable("username") String
    // username,
    // @Valid @RequestBody ForgetPasswordRequest forgetPasswordRequest) {
    // log.info("reset-password-with-otp api call initaited for username: " +
    // username);
    // userService.resetPasswordWithOTP(username, forgetPasswordRequest);
    // return ResponseEntity.ok().build();
    // }

    // @PutMapping(value = "/api/auth/send-otp/{username}")
    // @Operation(description = "Send OTP(sms/email) to username(mobile-number/email
    // address)", summary = "Send OTP(sms/email) to username(mobile-number/email
    // address)")
    // public ResponseEntity sendOtp(@PathVariable("username") String username) {
    // otpService.sendOTP(username);
    // return ResponseEntity.ok().build();
    // }

    // @PostMapping(value = "/api/auth/signup/otp")
    // @Operation(description = "Sign up into the system with otp api", summary =
    // "Sign up into the system with otp api")
    // public ResponseEntity<UserResponse> signupWithOTP(@Valid @RequestBody
    // OtpSignupRequestDto otpSignupRequestDto) {
    // return ResponseEntity.ok(userService.signupWithOTP(otpSignupRequestDto));
    // }
}

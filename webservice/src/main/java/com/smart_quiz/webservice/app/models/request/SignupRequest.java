package com.smart_quiz.webservice.app.models.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.smart_quiz.webservice.app.models.validators.Username;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupRequest {

    @NotBlank(message = "Username cannot be empty.")
    @Size(min = 4, max = 50, message = "Username '${validatedValue}' not allowed. Username must be between {min} and {max} characters long.")
    @Username
    @JsonDeserialize(using = ToLowerCaseDeserializer.class)
    private String username;

    @Size(max = 50)
    @Email(message = "Must be a valid email address.")
    @JsonDeserialize(using = ToLowerCaseDeserializer.class)
    private String email;

    private String mobileNumber;

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 6, max = 20, message = "Password must be between {min} characters and {max} characters long.")
    private String password;

    @NotBlank(message = "firstName cannot be empty.")
    private String firstName;

    private String lastName;

    @NotBlank
    private String role;

    private String quizCode;
}

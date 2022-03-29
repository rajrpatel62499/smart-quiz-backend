package com.smart_quiz.webservice.app.models.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Username cannot be empty.")
    @JsonDeserialize(using = ToLowerCaseDeserializer.class)
    private String username;
    @NotBlank(message = "Password cannot be empty.")
    private String password;

    private String quizCode;
}

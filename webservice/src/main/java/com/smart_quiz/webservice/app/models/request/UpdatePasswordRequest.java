package com.smart_quiz.webservice.app.models.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class UpdatePasswordRequest {
    @NotBlank(message = "Username cannot be empty.")
    @JsonDeserialize(using = ToLowerCaseDeserializer.class)
    private final String username;
    @NotBlank(message = "Old password cannot be empty.")
    private final String oldPassword;
    @NotBlank(message = "New password cannot be empty.")
    @Size(min = 6, max = 20, message = "Password must be between {min} characters and {max} characters long.")
    private final String newPassword;
}

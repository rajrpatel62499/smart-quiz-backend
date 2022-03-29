package com.smart_quiz.webservice.app.models.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class ForgetPasswordRequest {

	@NotBlank(message = "Otp cannot be empty.")
	@Size(min = 4, max = 6, message = "OTP must be between {min} and {max} chracters long")
	private String otp;

	@NotBlank(message = "Password cannot be empty.")
	@Size(min = 6, max = 20, message = "Password must be between {min} characters and {max} characters long.")
	private String password;
}

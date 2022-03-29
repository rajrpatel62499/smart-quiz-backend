package com.smart_quiz.webservice.app.models.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends ApiException {

	public BadRequestException(String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}

	public BadRequestException(String message, HttpStatus httpStatus) {
		super(message, httpStatus);
	}
}

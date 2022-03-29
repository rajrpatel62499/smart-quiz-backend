package com.smart_quiz.webservice.app.models.exception;

public class InvalidRequestException extends Exception {
    public InvalidRequestException(String message) {
        super(message);
    }
}

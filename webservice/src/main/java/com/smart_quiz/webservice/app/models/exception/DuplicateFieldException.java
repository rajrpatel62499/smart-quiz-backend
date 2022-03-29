package com.smart_quiz.webservice.app.models.exception;

public class DuplicateFieldException extends Exception {
    public DuplicateFieldException(String message) {
        super(message);
    }
}

package com.smart_quiz.webservice.app.models.validators;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidator implements ConstraintValidator<Username, String> {

    private static final String REGEX = "^(?=.{4,50}$)(?![_.@])(?!.*[_.@]{2})[a-zA-Z0-9._@]+(?<![_.@])$";

    public boolean isValid(String username, ConstraintValidatorContext cvc) {
        if (!StringUtils.isEmpty(username)) {
            Matcher matcher = Pattern.compile(REGEX).matcher(username);
            return matcher.matches();
        }
        return true;
    }
}
package com.smart_quiz.webservice.global.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum QuizType {
    REGULAR, SMART_SCALE;

    public static List<String> getNames() {
        return Arrays.stream(QuizType.values()).map(QuizType::name).collect(Collectors.toList());
    }
}

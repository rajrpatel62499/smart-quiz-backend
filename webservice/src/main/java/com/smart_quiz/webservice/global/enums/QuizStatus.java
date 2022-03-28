package com.smart_quiz.webservice.global.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum QuizStatus {
	IN_DRAFT,
	PUBLISHED,
	ANSWER_KEY_SHARED,
	RESULT_SHARED,
	DELETED;

	public static List<String> getNames() {
		return Arrays.stream(QuizStatus.values()).map(QuizStatus::name).collect(Collectors.toList());
	}
}

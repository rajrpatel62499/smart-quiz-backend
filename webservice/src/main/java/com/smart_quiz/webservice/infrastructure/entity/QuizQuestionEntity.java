package com.smart_quiz.webservice.infrastructure.entity;

import javax.persistence.*;

import com.smart_quiz.webservice.global.enums.QuestionDifficulty;
import com.smart_quiz.webservice.global.enums.QuestionType;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "quiz_questions")
public class QuizQuestionEntity extends EntityBaseClass {

	@Column(name = "quiz_id")
	private Long quizId;

	@Column(columnDefinition = "TEXT")
	private String questionText;

	@Column(name = "author")
	private String author;

	@Column(name = "marks")
	private Integer marks;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "difficulty_level")
	private QuestionDifficulty difficultyLevel;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "question_type")
	private QuestionType questionType;

	@Column(nullable = false, columnDefinition = "bit(1) not null default b'0'")
	private Boolean autoMarking;

	@Column(nullable = false, columnDefinition = "bit(1) not null default b'0'")
	private Boolean showWorkingOut = false;

	@Column(name = "question_option", columnDefinition = "TEXT")
	private String questionOption;

	@Column(name = "section_id")
	private Long quizSectionId;

	@Column(name = "root_question_id")
	private Long rootQuestionId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_question_id")
	private QuizQuestionEntity parentQuestion;
}

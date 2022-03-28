package com.smart_quiz.webservice.infrastructure.entity;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@Table(name = "quiz_setting")
public class QuizSettingEntity extends EntityBaseClass {

	/*
	 * @Column(name="quiz_id")
	 * private Long quizId;
	 */

	@JsonIgnoreProperties("quizSetting")
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "quiz_id", referencedColumnName = "id")
	@OrderBy(value = "id asc")
	private QuizEntity quiz;

	@Column(name = "quiz_code")
	private String quizCode;

	@Column(name = "timer")
	private Integer noOfMinutes;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "share_answer_key")
	private Boolean shareAnswerKey = false;

	@Column(name = "watermark", length = 100)
	private String watermark;

	@Column(name = "theme", length = 20)
	private String theme;

	@Column(name = "logo", length = 50)
	private String logo;

	@Column(name = "meta")
	private String meta;

	@Column(name = "question_randomize", columnDefinition = "bit(1) default b'0'")
	private Boolean questionRandomize = false;

}

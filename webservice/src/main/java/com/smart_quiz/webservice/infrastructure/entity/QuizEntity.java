package com.smart_quiz.webservice.infrastructure.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.smart_quiz.webservice.global.enums.QuizStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "quiz")
public class QuizEntity extends EntityBaseClass {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "title")
    private String quizTitle;

    @Column(name = "description")
    private String description;

    @Column(name = "status", columnDefinition = "int default 0")
    private QuizStatus quizStatus = QuizStatus.IN_DRAFT;

    @Column(name = "total_marks")
    private Double totalMarks;

    @Column(name = "published_at")
    private Date publishedAt;
}
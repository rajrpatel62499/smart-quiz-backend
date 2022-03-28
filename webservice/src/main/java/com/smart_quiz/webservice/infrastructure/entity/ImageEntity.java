package com.smart_quiz.webservice.infrastructure.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.smart_quiz.webservice.global.enums.ImageType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "images")
// @Check(constraints = "question_id IS NOT NULL OR answer_id IS NOT NULL OR
// option_id IS NOT NULL")
public class ImageEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT")
    private String imageKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "image_type", length = 50)
    private ImageType imageType;

    @Column(columnDefinition = "TEXT")
    private String data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private AnswerEntity answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    private OptionEntity option;
}

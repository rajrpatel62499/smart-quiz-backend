package com.smart_quiz.webservice.infrastructure.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import com.smart_quiz.webservice.global.enums.QuestionDifficulty;
import com.smart_quiz.webservice.global.enums.QuestionType;

@NoArgsConstructor
@Data
@Entity
@Table(name = "questions")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT")
    private String questionText;

    private String author;

    private int marks;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTimeStamp;

    @Temporal(TemporalType.TIMESTAMP)
    private Date editTimeStamp;

    @Enumerated(EnumType.ORDINAL)
    private QuestionDifficulty questionDifficulty;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private QuestionType questionType;
}

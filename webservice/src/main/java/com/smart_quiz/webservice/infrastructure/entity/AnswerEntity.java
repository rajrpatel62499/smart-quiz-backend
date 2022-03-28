package com.smart_quiz.webservice.infrastructure.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "answers", uniqueConstraints = @UniqueConstraint(columnNames = { "question_id", "answerText" }))
public class AnswerEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT")
    private String answerText;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ImageEntity> images = new HashSet<>();

    public void addImages(ImageEntity imageEntity) {
        this.images.add(imageEntity);
        imageEntity.setAnswer(this);
    }

    public void removeImages(ImageEntity imageEntity) {
        this.images.remove(imageEntity);
        imageEntity.setAnswer(null);
    }
}

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
@Table(name = "options")
// don't use data with entity and many to many toString() causes stackOverflow
public class OptionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT")
    private String optionText;

    @Column(columnDefinition = "boolean default false")
    private Boolean isAnswer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    @OneToMany(mappedBy = "option", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ImageEntity> images = new HashSet<>();

    public void addImages(ImageEntity imageEntity) {
        this.images.add(imageEntity);
        imageEntity.setOption(this);
    }

    public void removeImages(ImageEntity imageEntity) {
        this.images.remove(imageEntity);
        imageEntity.setOption(null);
    }

    // required
    public void setIsAnswer(Boolean isAnswer) {
        this.isAnswer = isAnswer;
    }
}

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

    @Column(nullable = false, columnDefinition = "bit(1) not null default b'0'")
    private Boolean autoMarking = false;

    @Column(nullable = false, columnDefinition = "bit(1) not null default b'0'")
    private Boolean showWorkingOut = false;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy(value = "id asc") // required to sort when retrieving relation entity
    private Set<OptionEntity> options = new HashSet<>();

    public void addOptions(OptionEntity optionEntity) {
        this.options.add(optionEntity);
        optionEntity.setQuestion(this);
    }

    public void removeOptions(OptionEntity optionEntity) {
        this.options.remove(optionEntity);
        optionEntity.setQuestion(null);
    }

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy(value = "id asc") // required to sort when retrieving relation entity
    private Set<TopicEntity> topics = new HashSet<>();

    public void addTopics(TopicEntity topicEntity) {
        this.topics.add(topicEntity);
        topicEntity.setQuestion(this);
    }

    public void removeTopics(TopicEntity topicEntity) {
        this.topics.remove(topicEntity);
        topicEntity.setQuestion(null);
    }

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy(value = "id asc") // required to sort when retrieving relation entity
    private Set<AnswerEntity> answers = new HashSet<>();

    public void addAnswers(AnswerEntity answerEntity) {
        this.answers.add(answerEntity);
        answerEntity.setQuestion(this);
    }

    public void removeAnswers(AnswerEntity answerEntity) {
        this.answers.remove(answerEntity);
        answerEntity.setQuestion(null);
    }

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy(value = "id asc") // required to sort when retrieving relation entity
    private Set<ImageEntity> images = new HashSet<>();

    public void addImages(ImageEntity imageEntity) {
        this.images.add(imageEntity);
        imageEntity.setQuestion(this);
    }

    public void removeImages(ImageEntity imageEntity) {
        this.images.remove(imageEntity);
        imageEntity.setQuestion(null);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_question_id")
    private QuestionEntity parentQuestion;

    // owner of resource_bank association table
    @ManyToMany
    @JoinTable(name = "resource_bank", joinColumns = @JoinColumn(name = "question_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserEntity> users = new HashSet<>();

    public void addUser(UserEntity userEntity) {
        this.users.add(userEntity);
        userEntity.getQuestions().add(this);
    }

    public void removeUser(UserEntity userEntity) {
        this.users.remove(userEntity);
        userEntity.getQuestions().remove(this);
    }

}

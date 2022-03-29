package com.smart_quiz.webservice.infrastructure.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.smart_quiz.webservice.global.enums.AuthProvider;
import com.smart_quiz.webservice.global.enums.AuthRole;
import com.smart_quiz.webservice.global.enums.UsernameType;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String password;

    private String firstName;

    private String lastName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginStamp;

    @Enumerated(EnumType.ORDINAL)
    private AuthRole role;

    private Boolean isActive = true;

    @Column(unique = true, length = 15)
    private String mobileNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private AuthProvider authProvider;

    @Column(length = 500)
    private String image;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private UsernameType usernameType;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

    @ManyToMany(mappedBy = "users")
    @OrderBy(value = "id asc")
    private Set<QuestionEntity> questions = new HashSet<>();
}

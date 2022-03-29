package com.smart_quiz.webservice.app.models.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.smart_quiz.webservice.infrastructure.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private long id;

    private String username;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginStamp;

    private String firstName;

    private String lastName;

    private String authRole;

    public UserResponse(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.email = userEntity.getEmail();
        this.lastLoginStamp = userEntity.getLastLoginStamp();
        this.creationDate = userEntity.getCreationDate();
        if (userEntity.getFirstName() != null) {
            this.firstName = userEntity.getFirstName();
        }
        if (userEntity.getLastName() != null) {
            this.lastName = userEntity.getLastName();
        }
        this.authRole = userEntity.getRole().toString().toUpperCase();
    }
}

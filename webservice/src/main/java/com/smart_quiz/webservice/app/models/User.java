package com.smart_quiz.webservice.app.models;

import java.util.Date;
import javax.validation.constraints.NotBlank;

import com.smart_quiz.webservice.global.enums.AuthRole;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class User {
    private long id;

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    private String mobileNumber;

    @NotBlank
    private String password;

    private String firstName;

    private String lastName;

    private Date creationDate;

    private Date lastLoginStamp;

    @NotBlank
    private AuthRole role;

    public void maskPassword() {
        this.password = StringUtils.overlay(this.password, StringUtils.repeat("*", this.password.length()), 0,
                this.password.length());
    }
}

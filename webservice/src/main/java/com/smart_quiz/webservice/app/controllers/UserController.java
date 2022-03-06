package com.smart_quiz.webservice.app.controllers;

import com.smart_quiz.webservice.global.constants.ApiPathConstants;
import com.smart_quiz.webservice.infrastructure.entity.UserEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ApiPathConstants.UserControllerPath)
public class UserController {

    @GetMapping()
    public String getUser() {

        UserEntity userEntity = new UserEntity();

        System.out.print(userEntity);
        return "Hello World";

    }

}

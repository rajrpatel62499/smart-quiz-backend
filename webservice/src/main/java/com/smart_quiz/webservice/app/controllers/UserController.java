package com.smart_quiz.webservice.app.controllers;

import com.smart_quiz.webservice.constants.ApiPathConstants;
import com.smart_quiz.webservice.constants.AppConstants;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = ApiPathConstants.UserControllerPath)
public class UserController {

    @GetMapping()
    public String helloworld() {
        return "Hello World";
    }

}

package com.smart_quiz.webservice.app.controllers;

import com.smart_quiz.webservice.infrastructure.repository.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
public class HomeController {

    private UserRepository userRepository;

    @GetMapping("/")
    public String greet() {
        return "Hello World spring" ;
    }

}

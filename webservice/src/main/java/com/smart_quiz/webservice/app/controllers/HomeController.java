package com.smart_quiz.webservice.app.controllers;

import com.smart_quiz.webservice.infrastructure.repository.UserRepository;
import com.smart_quiz.webservice.security.UserPrincipal;
import lombok.Data;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Authenticator;

@Data
@RestController
public class HomeController {

    private UserRepository userRepository;

    @GetMapping("/")
    public String greet() {

        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        return "Hello " + "world";
    }

}

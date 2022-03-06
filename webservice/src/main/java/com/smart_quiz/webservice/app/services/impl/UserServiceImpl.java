package com.smart_quiz.webservice.app.services.impl;

import com.smart_quiz.webservice.app.services.UserService;
import com.smart_quiz.webservice.infrastructure.repository.UserRepository;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(
            UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}

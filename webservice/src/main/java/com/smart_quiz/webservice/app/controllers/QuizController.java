package com.smart_quiz.webservice.app.controllers;

import com.smart_quiz.webservice.app.services.impl.QuestionService;
import com.smart_quiz.webservice.app.services.impl.QuizService;
import com.smart_quiz.webservice.global.constants.ApiPathConstants;
import com.smart_quiz.webservice.infrastructure.entity.QuizEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ApiPathConstants.QuizController)
public class QuizController {
    @Autowired
    private QuizService quizService;

    @GetMapping
    public ResponseEntity<List<QuizEntity>> getAllQuizEntity(){
        return quizService.getAllQuizEntity();
    }

    @PostMapping
    public ResponseEntity<QuizEntity> saveQuizEntity(@RequestBody QuizEntity item){
        return quizService.saveQuizEntity(item);
    }
}

package com.smart_quiz.webservice.app.controllers;

import com.smart_quiz.webservice.app.services.impl.QuestionService;
import com.smart_quiz.webservice.global.constants.ApiPathConstants;
import com.smart_quiz.webservice.infrastructure.entity.QuestionEntity;
import com.smart_quiz.webservice.infrastructure.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= ApiPathConstants.QuestionController)
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<QuestionEntity>> getAllQuestion(){
        return questionService.getAll();
    }
    @PostMapping
    public ResponseEntity<QuestionEntity> create(@RequestBody QuestionEntity item) {
        return questionService.save(item);
    }

    @PutMapping({"{id}"})
    public ResponseEntity<QuestionEntity> update(@PathVariable("id") Long id, @RequestBody QuestionEntity item){
        return questionService.update(id,item);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        return questionService.delete(id);
    }

}

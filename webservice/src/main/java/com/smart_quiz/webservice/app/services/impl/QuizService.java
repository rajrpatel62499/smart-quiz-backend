package com.smart_quiz.webservice.app.services.impl;

import com.smart_quiz.webservice.infrastructure.entity.QuizEntity;
import com.smart_quiz.webservice.infrastructure.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    public ResponseEntity<List<QuizEntity>> getAllQuizEntity() {
        try
        {
            List<QuizEntity> items = new ArrayList<QuizEntity>();
            quizRepository.findAll().forEach(items::add);
            if (items.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(items, HttpStatus.OK);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<QuizEntity> saveQuizEntity(QuizEntity item) {
        try{
            return new ResponseEntity<>(quizRepository.save(item),HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    public ResponseEntity<HttpStatus> deleteQuizEntity(Long id) {
        try{
           quizRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
        }
    }

    public ResponseEntity<QuizEntity> putQuizEntity(Long id, QuizEntity item) {
        Optional<QuizEntity> existingQuizEntity = quizRepository.findById(id);
        if(existingQuizEntity.isPresent()){
            QuizEntity existingQuizItem = existingQuizEntity.get();
            existingQuizItem = item;
         
            existingQuizItem.setId(id);
            return new ResponseEntity<>(quizRepository.save(existingQuizItem),HttpStatus.OK);

        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

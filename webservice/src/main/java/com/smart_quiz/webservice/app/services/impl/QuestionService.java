package com.smart_quiz.webservice.app.services.impl;

import com.smart_quiz.webservice.infrastructure.entity.QuestionEntity;
import com.smart_quiz.webservice.infrastructure.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<List<QuestionEntity>> getAll() {
        try {
            List<QuestionEntity> items = new ArrayList<QuestionEntity>();

            questionRepository.findAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<QuestionEntity> save(QuestionEntity item) {
        try {

            QuestionEntity savedItem = questionRepository.save(item);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    public ResponseEntity<QuestionEntity> update(Long id, QuestionEntity item) {
        Optional<QuestionEntity> existingItemOptional = questionRepository.findById(id);
        if (existingItemOptional.isPresent()) {
            QuestionEntity existingItem = existingItemOptional.get();
            existingItem = item;
            System.out.println(existingItem);
            existingItem.setId(id);

            // existingItem.setSomeField(item.getSomeField());
            return new ResponseEntity<>(questionRepository.save(existingItem), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> delete(Long id) {
        try {
            questionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}

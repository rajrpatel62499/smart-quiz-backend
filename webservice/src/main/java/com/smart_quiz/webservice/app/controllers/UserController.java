package com.smart_quiz.webservice.app.controllers;

import com.smart_quiz.webservice.global.constants.ApiPathConstants;
import com.smart_quiz.webservice.infrastructure.entity.UserEntity;
import com.smart_quiz.webservice.infrastructure.repository.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping(value = ApiPathConstants.UserControllerPath)
// public class UserController {

//     @GetMapping()
//     public String getUser(UserRepository userRepository) {

//         UserEntity userEntity = new UserEntity();

//         System.out.print(userRepository.toString());
//         System.out.print(userEntity);
//         return "Hello World";

//     }

// }

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = ApiPathConstants.UserControllerPath)
class UserController {

    @Autowired
    UserRepository repository;

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAll() {
        try {
            List<UserEntity> items = new ArrayList<UserEntity>();

            System.out.println("Raj changed the code in urvish laptop");
            repository.findAll().forEach(items::add);
            System.out.println(items);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<UserEntity> getById(@PathVariable("id") Long id) {
        Optional<UserEntity> existingItemOptional = repository.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UserEntity> create(@RequestBody UserEntity item) {
        try {
            System.out.println(item);
            UserEntity savedItem = repository.save(item);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<UserEntity> update(@PathVariable("id") Long id, @RequestBody UserEntity item) {
        Optional<UserEntity> existingItemOptional = repository.findById(id);
        if (existingItemOptional.isPresent()) {
            UserEntity existingItem = existingItemOptional.get();
            System.out
                    .println("TODO for developer - update logic is unique to entity and must be implemented manually.");
            // existingItem.setSomeField(item.getSomeField());
            return new ResponseEntity<>(repository.save(existingItem), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}

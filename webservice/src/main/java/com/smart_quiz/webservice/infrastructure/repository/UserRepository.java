package com.smart_quiz.webservice.infrastructure.repository;

import org.springframework.stereotype.Repository;

import com.smart_quiz.webservice.infrastructure.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}

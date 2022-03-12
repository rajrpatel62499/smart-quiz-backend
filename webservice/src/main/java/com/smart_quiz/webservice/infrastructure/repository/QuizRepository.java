package com.smart_quiz.webservice.infrastructure.repository;

import com.smart_quiz.webservice.infrastructure.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<QuizEntity,Long> {
}

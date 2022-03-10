package com.smart_quiz.webservice.infrastructure.repository;

import com.smart_quiz.webservice.infrastructure.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity,Long> {


}

package com.halocom.Exercises.Repository;

import com.halocom.Exercises.Model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer>findByQuestion(Integer question);
}

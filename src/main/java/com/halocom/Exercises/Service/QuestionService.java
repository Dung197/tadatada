package com.halocom.Exercises.Service;

import com.halocom.Exercises.Model.Question;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    List<Question> findAllQuestion();

    Optional<Question> findQuestionById(Integer id);
    void createQuestion(Question question);
    ResponseEntity<Question> updateQuestion(Integer id, Question question);
    void deleteQuestion(Integer id);
    List<Question> findQuestionByTag(String tag);
}

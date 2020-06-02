package com.halocom.Exercises.Service;

import com.halocom.Exercises.Model.Answer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public interface AnswerService {
    List<Answer> findAllAnswer();
    List<Answer> findAnswerByQuestion(Integer question);
    ResponseEntity<Answer> createAnswer(Answer answer, UriComponentsBuilder builder);
}

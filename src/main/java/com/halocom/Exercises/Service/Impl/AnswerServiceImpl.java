package com.halocom.Exercises.Service.Impl;

import com.halocom.Exercises.Model.Answer;
import com.halocom.Exercises.Model.Question;
import com.halocom.Exercises.Repository.AnswerRepository;
import com.halocom.Exercises.Repository.QuestionRepository;
import com.halocom.Exercises.Service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {

    private AnswerRepository answerRepository;
    private QuestionRepository questionRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository, QuestionRepository questionRepository){
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Answer> findAllAnswer() {
        return answerRepository.findAll();
    }

    @Override
    public List<Answer> findAnswerByQuestion(Integer question) {
        List<Answer> answers = answerRepository.findByQuestion(question);
        return answers;
    }

    @Override
    public ResponseEntity<Answer> createAnswer(Answer answer, UriComponentsBuilder builder) {
        Optional<Question> question = questionRepository.findById(answer.getQuestion());
        if (!question.isPresent()){
            return new ResponseEntity<>(answer,
                    HttpStatus.NO_CONTENT);
        }

        if (question.get().getActive()==false){
            return new ResponseEntity<>(answer,
                    HttpStatus.NO_CONTENT);
        }
        answer.setCreateDate(new Timestamp(System.currentTimeMillis()));
        answerRepository.save(answer);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/answer/{id}")
                .buildAndExpand(answer.getAnswerId()).toUri());
        return new ResponseEntity<>(answer, HttpStatus.CREATED);
    }
}

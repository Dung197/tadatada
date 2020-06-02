package com.halocom.Exercises.Service.Impl;

import com.halocom.Exercises.Model.Question;
import com.halocom.Exercises.Repository.QuestionRepository;
import com.halocom.Exercises.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> findAllQuestion() {
        return questionRepository.findAll();
    }

    @Override
    public Optional<Question> findQuestionById(Integer id) {
        return questionRepository.findById(id);
    }

    @Override
    public void createQuestion(Question question) {
        question.setCreateDate(new Timestamp(System.currentTimeMillis()));
        questionRepository.save(question);

    }

    @Override
    public ResponseEntity updateQuestion(Integer id, Question question) {
        Optional<Question> question1 = questionRepository.findById(id);
        if (!question1.isPresent()){
            return new ResponseEntity<>(question,
                    HttpStatus.NO_CONTENT);
        }
        question1.get().setActive(question.getActive());
        questionRepository.save(question1.get());
        return new ResponseEntity(question1,HttpStatus.OK);

    }

    @Override
    public void deleteQuestion(Integer id) {
        Optional<Question> question = questionRepository.findById(id);

        if (question.isPresent()){
            questionRepository.delete(question.get());
        }
    }

    @Override
    public List<Question> findQuestionByTag(String tag) {
        List<Question> questions = questionRepository.findByTagContaining(tag);
        return questions;
    }
}

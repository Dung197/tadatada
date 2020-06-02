package com.halocom.Exercises.Service.Impl;

import com.halocom.Exercises.Model.Question;
import com.halocom.Exercises.Model.UserVoteQuestion;
import com.halocom.Exercises.Repository.QuestionRepository;
import com.halocom.Exercises.Repository.UserVoteQuestionRepository;
import com.halocom.Exercises.Service.UserVoteQuestionService;
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
public class UserVoteQuestionServiceImpl implements UserVoteQuestionService {

    private UserVoteQuestionRepository userVoteQuestionRepository;
    private QuestionRepository questionRepository;

    @Autowired
    public UserVoteQuestionServiceImpl(UserVoteQuestionRepository userVoteQuestionRepository, QuestionRepository questionRepository) {
        this.userVoteQuestionRepository = userVoteQuestionRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Optional<UserVoteQuestion> getUserVoteQuestion(Integer questionId, Integer userId) {
        Optional<UserVoteQuestion> currentUserVoteQuestion =
                userVoteQuestionRepository.findByQuestionAndUser(
                        questionId, userId);
        return  currentUserVoteQuestion;
    }

    @Override
    public List<UserVoteQuestion> findAllUserVoteQuestion() {
        return userVoteQuestionRepository.findAll();
    }

    @Override
    public void createUserVoteQuestion(UserVoteQuestion userVoteQuestion) {
        userVoteQuestion.setCreateDate(new Timestamp(System.currentTimeMillis()));
        userVoteQuestionRepository.save(userVoteQuestion);
    }


    @Override
    public ResponseEntity<UserVoteQuestion> updateUserVoteQuestion(Integer questionId, Integer userId, UserVoteQuestion userVoteQuestion, UriComponentsBuilder builder) {
        Optional<Question> question = questionRepository.findById(questionId);
        if (!question.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Optional<UserVoteQuestion> currentUserVoteQuestion =
                userVoteQuestionRepository.findByQuestionAndUser(
                        questionId, userId);
        if (!currentUserVoteQuestion.isPresent()) {
            userVoteQuestion.setCreateDate(new Timestamp(System.currentTimeMillis()));
            userVoteQuestion.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            userVoteQuestion.setQuestion(questionId);
            userVoteQuestion.setUser(userId);
            userVoteQuestionRepository.save(userVoteQuestion);

            int upVoteSize = userVoteQuestionRepository.findByQuestionAndUpVote(questionId, true).size();
            int downVoteSize = userVoteQuestionRepository.findByQuestionAndDownVote(questionId, true).size();
            if (upVoteSize>=0 && downVoteSize>=0){
                question.get().setUpVote(upVoteSize);
                question.get().setDownVote(downVoteSize);
                questionRepository.save(question.get());
            }
            return new ResponseEntity<>(userVoteQuestion, HttpStatus.CREATED);
        }

        boolean upVote = userVoteQuestion.getUpVote();
        boolean downVote = userVoteQuestion.getDownVote();

        currentUserVoteQuestion.get().setDownVote(downVote);
        currentUserVoteQuestion.get().setUpVote(upVote);
        currentUserVoteQuestion.get().setUpdateDate(new Timestamp(System.currentTimeMillis()));
        userVoteQuestionRepository.save(currentUserVoteQuestion.get());
        int upVoteSize = userVoteQuestionRepository.findByQuestionAndUpVote(questionId, true).size();
        int downVoteSize = userVoteQuestionRepository.findByQuestionAndDownVote(questionId, true).size();
        if (upVoteSize>=0 && downVoteSize>=0){
            question.get().setUpVote(upVoteSize);
            question.get().setDownVote(downVoteSize);
            questionRepository.save(question.get());
        }
        return new ResponseEntity<>(currentUserVoteQuestion.get(), HttpStatus.OK);
    }
}

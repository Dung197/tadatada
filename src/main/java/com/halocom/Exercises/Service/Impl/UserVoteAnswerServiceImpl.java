package com.halocom.Exercises.Service.Impl;

import com.halocom.Exercises.Model.Answer;
import com.halocom.Exercises.Model.Question;
import com.halocom.Exercises.Model.UserVoteAnswer;
import com.halocom.Exercises.Model.UserVoteQuestion;
import com.halocom.Exercises.Repository.AnswerRepository;
import com.halocom.Exercises.Repository.UserVoteAnswerRepository;
import com.halocom.Exercises.Service.UserVoteAnswerService;
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
public class UserVoteAnswerServiceImpl implements UserVoteAnswerService {
    private UserVoteAnswerRepository userVoteAnswerRepository;
    private AnswerRepository answerRepository;

    @Autowired
    public UserVoteAnswerServiceImpl(UserVoteAnswerRepository userVoteAnswerRepository, AnswerRepository answerRepository) {
        this.userVoteAnswerRepository = userVoteAnswerRepository;
        this.answerRepository = answerRepository;
    }


    @Override
    public Optional<UserVoteAnswer> getUserVoteAnswer(Integer answerId, Integer userId) {
        Optional<UserVoteAnswer> userVoteAnswer =
                userVoteAnswerRepository.findByAnswerAndUser(
                        answerId, userId);
        return  userVoteAnswer;
    }

    @Override
    public List<UserVoteAnswer> findAllUserVoteAnswer() {
        return userVoteAnswerRepository.findAll();
    }

    @Override
    public void createUserVoteAnswer(UserVoteAnswer userVoteAnswer) {
        userVoteAnswer.setCreateDate(new Timestamp(System.currentTimeMillis()));
        userVoteAnswerRepository.save(userVoteAnswer);


    }

    @Override
    public ResponseEntity<UserVoteAnswer> updateUserVoteAnswer(Integer answerId, Integer userId, UserVoteAnswer userVoteAnswer, UriComponentsBuilder builder) {
        Optional<Answer> answer = answerRepository.findById(answerId);
        if (!answer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Optional<UserVoteAnswer> currentUserVoteAnswer =
                userVoteAnswerRepository.findByAnswerAndUser(
                        answerId, userId);
        if (!currentUserVoteAnswer.isPresent()) {
            userVoteAnswer.setCreateDate(new Timestamp(System.currentTimeMillis()));
            userVoteAnswer.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            userVoteAnswer.setAnswer(answerId);
            userVoteAnswer.setUser(userId);
            userVoteAnswerRepository.save(userVoteAnswer);

            int upVoteSize = userVoteAnswerRepository.findByAnswerAndUpVote(answerId, true).size();
            int downVoteSize = userVoteAnswerRepository.findByAnswerAndDownVote(answerId, true).size();
            if (upVoteSize>=0 && downVoteSize>=0){
                answer.get().setUpVote(upVoteSize);
                answer.get().setDownVote(downVoteSize);
                answerRepository.save(answer.get());
            }
            return new ResponseEntity<>(userVoteAnswer, HttpStatus.CREATED);
        }

        boolean upVote = userVoteAnswer.getUpVote();
        boolean downVote = userVoteAnswer.getDownVote();

        currentUserVoteAnswer.get().setDownVote(downVote);
        currentUserVoteAnswer.get().setUpVote(upVote);
        currentUserVoteAnswer.get().setUpdateDate(new Timestamp(System.currentTimeMillis()));
        userVoteAnswerRepository.save(currentUserVoteAnswer.get());
        int upVoteSize = userVoteAnswerRepository.findByAnswerAndUpVote(answerId, true).size();
        int downVoteSize = userVoteAnswerRepository.findByAnswerAndDownVote(answerId, true).size();
        if (upVoteSize>=0 && downVoteSize>=0){
            answer.get().setUpVote(upVoteSize);
            answer.get().setDownVote(downVoteSize);
            answerRepository.save(answer.get());
        }
        return new ResponseEntity<>(currentUserVoteAnswer.get(), HttpStatus.OK);
    }

}

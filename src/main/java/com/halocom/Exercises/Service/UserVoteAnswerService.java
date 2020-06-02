package com.halocom.Exercises.Service;

import com.halocom.Exercises.Model.UserVoteAnswer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

public interface UserVoteAnswerService {
    Optional<UserVoteAnswer> getUserVoteAnswer(Integer answerId, Integer userId);
    List<UserVoteAnswer> findAllUserVoteAnswer();
    void createUserVoteAnswer(UserVoteAnswer userVoteAnswer);
    ResponseEntity<UserVoteAnswer> updateUserVoteAnswer(Integer answerId, Integer userId, UserVoteAnswer userVoteAnswer, UriComponentsBuilder builder);

}

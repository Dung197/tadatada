package com.halocom.Exercises.Service;

import com.halocom.Exercises.Model.UserVoteQuestion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

public interface UserVoteQuestionService {
    Optional<UserVoteQuestion> getUserVoteQuestion(Integer questionId, Integer userId);
    List<UserVoteQuestion> findAllUserVoteQuestion();
    void createUserVoteQuestion(UserVoteQuestion userVoteQuestion);
    ResponseEntity<UserVoteQuestion> updateUserVoteQuestion(Integer questionId, Integer userId,UserVoteQuestion userVoteQuestion, UriComponentsBuilder builder);

}

package com.halocom.Exercises.Controller;

import com.halocom.Exercises.Model.User;
import com.halocom.Exercises.Model.UserVoteAnswer;
import com.halocom.Exercises.Model.UserVoteQuestion;
import com.halocom.Exercises.Service.UserVoteAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
public class UserVoteAnswerController {
    private UserVoteAnswerService userVoteAnswerService;

    @Autowired
    public UserVoteAnswerController(UserVoteAnswerService userVoteAnswerService){
        this.userVoteAnswerService = userVoteAnswerService;
    }

    @RequestMapping(value = "/api/v1/answer/vote", method = RequestMethod.GET)
    public ResponseEntity<List<UserVoteAnswer>> findAll(){
        List<UserVoteAnswer> userVoteAnswers = userVoteAnswerService.findAllUserVoteAnswer();
        if (userVoteAnswers.isEmpty()){
            return new ResponseEntity<>(userVoteAnswers, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userVoteAnswers,HttpStatus.OK);
    }


    @RequestMapping(value = "/api/v1/answer/{answer_id}/user/{user_id}/vote",  method = RequestMethod.POST)
    public ResponseEntity<UserVoteAnswer> updateUserVoteAnswer(@PathVariable("answer_id") Integer answerId,
                                                                   @PathVariable("user_id") Integer userId,
                                                                   @RequestBody UserVoteAnswer userVoteAnswer, UriComponentsBuilder builder) {
        ResponseEntity responseEntity = userVoteAnswerService.updateUserVoteAnswer(answerId, userId, userVoteAnswer, builder);
        return responseEntity;

    }

    @RequestMapping(value = "/api/v1/answer/{answer_id}/user/{user_id}/vote",  method = RequestMethod.GET)
    public ResponseEntity<UserVoteAnswer> getUserVoteQuestion(@PathVariable("answer_id") Integer answerId,
                                                                @PathVariable("user_id") Integer userId) {
        Optional<UserVoteAnswer> userVoteAnswer = userVoteAnswerService.getUserVoteAnswer(answerId, userId);
        if (!userVoteAnswer.isPresent()){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(userVoteAnswer.get(), HttpStatus.OK);

    }



}

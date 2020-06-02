package com.halocom.Exercises.Controller;

import com.halocom.Exercises.Model.UserVoteQuestion;
import com.halocom.Exercises.Service.UserVoteQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserVoteQuestionController {

    private UserVoteQuestionService userVoteQuestionService;

    @Autowired
    public UserVoteQuestionController(UserVoteQuestionService userVoteQuestionService){
        this.userVoteQuestionService = userVoteQuestionService;
    }

    @RequestMapping(value = "/api/v1/question/vote", method = RequestMethod.GET)
    public ResponseEntity<List<UserVoteQuestion>> findAll(){
        List<UserVoteQuestion> userVoteQuestions = userVoteQuestionService.findAllUserVoteQuestion();
        if (userVoteQuestions.isEmpty()){
            return new ResponseEntity<>(userVoteQuestions, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userVoteQuestions,HttpStatus.OK);
    }

   /* @RequestMapping(value = "/question/{id}/vote",  method = RequestMethod.POST)
    public ResponseEntity<UserVoteQuestion> createUserVoteQuestion(
            @RequestBody UserVoteQuestion userVoteQuestion,
            UriComponentsBuilder builder) {
        userVoteQuestionService.createUserVoteQuestion(userVoteQuestion);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/user/{id}")
                .buildAndExpand(userVoteQuestion.getId()).toUri());
        return new ResponseEntity<>(userVoteQuestion, HttpStatus.CREATED);
    } */

    @RequestMapping(value = "/api/v1/question/{question_id}/user/{user_id}/vote",  method = RequestMethod.POST)
    public ResponseEntity<UserVoteQuestion> updateUserVoteQuestion(@PathVariable("question_id") Integer questionId,
                                                                   @PathVariable("user_id") Integer userId,
            @RequestBody UserVoteQuestion userVoteQuestion, UriComponentsBuilder builder) {
        ResponseEntity responseEntity = userVoteQuestionService.updateUserVoteQuestion(questionId, userId, userVoteQuestion, builder);
        return responseEntity;

    }

    @RequestMapping(value = "/api/v1/question/{question_id}/user/{user_id}/vote",  method = RequestMethod.GET)
    public ResponseEntity<UserVoteQuestion> getUserVoteQuestion(@PathVariable("question_id") Integer questionId,
                                                                @PathVariable("user_id") Integer userId) {
        Optional<UserVoteQuestion> userVoteQuestion = userVoteQuestionService.getUserVoteQuestion(questionId, userId);
        if (!userVoteQuestion.isPresent()){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(userVoteQuestion.get(), HttpStatus.OK);

    }

}

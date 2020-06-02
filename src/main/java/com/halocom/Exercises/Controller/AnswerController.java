package com.halocom.Exercises.Controller;

import com.halocom.Exercises.Model.Answer;
import com.halocom.Exercises.Model.Question;
import com.halocom.Exercises.Service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin
public class AnswerController {
    private AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService){
        this.answerService = answerService;
    }

    @RequestMapping(value = "/api/v1/question/{question_id}/answer", method = RequestMethod.GET)
    public ResponseEntity<List<Answer>> findAllAnswerByQuestion(@PathVariable("question_id") Integer question){
        List<Answer> answers =answerService.findAnswerByQuestion(question);
        if (answers.isEmpty()){
            return new ResponseEntity<>(answers, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(answers,HttpStatus.OK);
    }

    @RequestMapping(value = "/api/v1/answer",  method = RequestMethod.POST)
    public ResponseEntity<Answer> createAnswer(
            @RequestBody Answer answer,
            UriComponentsBuilder builder) {
        ResponseEntity responseEntity = answerService.createAnswer(answer, builder);
        return responseEntity;
    }
}

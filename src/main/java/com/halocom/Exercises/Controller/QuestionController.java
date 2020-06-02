package com.halocom.Exercises.Controller;

import com.halocom.Exercises.Model.Question;
import com.halocom.Exercises.Model.User;
import com.halocom.Exercises.Service.QuestionService;
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
public class QuestionController {

    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }

    @RequestMapping(value = "/api/v1/question", method = RequestMethod.GET)
    public ResponseEntity<List<Question>> findAll(){
        List<Question> questions =questionService.findAllQuestion();
        if (questions.isEmpty()){
            return new ResponseEntity<>(questions, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(questions,HttpStatus.OK);

    }

    @RequestMapping(value = "/api/v1/question",  method = RequestMethod.POST)
    public ResponseEntity<Question> createQuestion(
            @RequestBody Question question,
            UriComponentsBuilder builder) {
        questionService.createQuestion(question);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/question/{id}")
                .buildAndExpand(question.getQuestionId()).toUri());
        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/v1/question/{id}",  method = RequestMethod.PUT)
    public ResponseEntity<Question> updateQuestion( @PathVariable("id") Integer id,
            @RequestBody Question question) {
        ResponseEntity responseEntity = questionService.updateQuestion(id,question);

        return responseEntity;
    }

    @RequestMapping(value = "/api/v1/question/{id}", method = RequestMethod.GET)
    public ResponseEntity<Question> getQuestionById(@PathVariable("id") Integer id){
        Optional<Question> question = questionService.findQuestionById(id);
        if (!question.isPresent()) {
            return new ResponseEntity<>(question.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(question.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/v1/question/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Question> removeQuestion(@PathVariable("id") Integer id){
        questionService.deleteQuestion(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/v1/question/tag/{tag}", method = RequestMethod.GET)
    public ResponseEntity<List<Question>> searchByTag(@PathVariable("tag") String tag){
        List<Question> questions = questionService.findQuestionByTag(tag);
        if (questions.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);

    }
}

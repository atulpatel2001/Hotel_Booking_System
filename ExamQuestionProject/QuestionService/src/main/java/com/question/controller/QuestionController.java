package com.question.controller;

import com.question.entities.Question;
import com.question.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<Question> addQusetion(@RequestBody  Question question) {
       return ResponseEntity.status(HttpStatus.CREATED).body(this.questionService.add(question));
    }

    @GetMapping
    public ResponseEntity<List<Question>> getAllQusetion(){
        return  ResponseEntity.ok(this.questionService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable Long id){
        return ResponseEntity.ok(this.questionService.get(id));
    }


    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<Question>> getQusetionByQuizId(@PathVariable("quizId") Long id){

        return ResponseEntity.ok(this.questionService.getByQuizId(id));
    }
}

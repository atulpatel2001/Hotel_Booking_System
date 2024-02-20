package com.quiz.controller;


import com.quiz.entities.Quiz;
import com.quiz.service.QuizService;
import com.quiz.service.client.QuestionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    //create
    @Autowired
    private QuizService quizService;


    @PostMapping
    public ResponseEntity<Quiz> create(@RequestBody Quiz quiz){
        return ResponseEntity.ok(this.quizService.add(quiz));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id){
        return ResponseEntity.ok(this.quizService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuiz(){

        return ResponseEntity.ok(this.quizService.getAll());
    }


}

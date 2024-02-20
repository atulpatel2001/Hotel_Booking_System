package com.quiz.service.imple;


import com.quiz.entities.Quiz;
import com.quiz.exception.ResourceNotFoundException;
import com.quiz.repository.QuizRepository;
import com.quiz.service.QuizService;
import com.quiz.service.client.QuestionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImple implements QuizService {
    @Autowired
    private QuizRepository quizrepository;
    @Autowired
    private QuestionClient questionClient;
    @Override
    public Quiz add(Quiz quiz) {
        return this.quizrepository.save(quiz) ;
    }

    @Override
    public List<Quiz> getAll() {
        List<Quiz> quizzes = this.quizrepository.findAll();
        List<Quiz> collect = quizzes.stream().map(quiz -> {
            quiz.setQuestiones(this.questionClient.getQuestionOfQuiz(quiz.getQuizId()));
            return quiz;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public Quiz get(Long id) {
        Quiz quiz = this.quizrepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quiz with given id not found on server!!:-  \"!!:- " + id));
        quiz.setQuestiones(this.questionClient.getQuestionOfQuiz(quiz.getQuizId()));
        return quiz;
    }
}


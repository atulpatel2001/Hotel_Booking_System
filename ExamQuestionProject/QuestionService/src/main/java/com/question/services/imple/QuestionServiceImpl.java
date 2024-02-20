package com.question.services.imple;

import com.question.entities.Question;
import com.question.exception.ResourceNotFoundException;
import com.question.repository.QusetionRepository;
import com.question.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QusetionRepository qusetionRepository;
    @Override
    public Question add(Question question) {
        return this.qusetionRepository.save(question);
    }

    @Override
    public List<Question> getAll() {
        return this.qusetionRepository.findAll();
    }

    @Override
    public Question get(Long id) {
        return this.qusetionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Question with given id not found on server!!:-  "+id));
    }

    @Override
    public List<Question> getByQuizId(Long id) {
        return this.qusetionRepository.findByQuizId(id);
    }
}

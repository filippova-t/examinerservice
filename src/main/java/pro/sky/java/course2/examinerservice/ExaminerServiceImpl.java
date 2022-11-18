package pro.sky.java.course2.examinerservice;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.exception.MoreQuestionsThanAvailableException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    private Random random;
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }


    @Override
    public Collection<Question> getQuestions(int amount)  {
        if (amount > questionService.getAll().size()) {
            throw new MoreQuestionsThanAvailableException("Запрошено больше вопросов, чем есть в базе");
        }
        Set<Question> questions = new HashSet<>();

        for (int i = 1; questions.size() < amount; i++) {
               questions.add(questionService.getRandomQuestion());
              
           }
        return questions;
    }
}

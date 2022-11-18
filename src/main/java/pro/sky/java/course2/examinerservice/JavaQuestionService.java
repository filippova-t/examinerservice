package pro.sky.java.course2.examinerservice;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.exception.QuestionNotFoundException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JavaQuestionService implements QuestionService{
    private final Set<Question> questions;

    public JavaQuestionService() {
        this.questions = new HashSet<>();
    }


    @Override
    public Question add(String question, String answer) {
        Question questionObject = new Question(question, answer);
        questions.add(questionObject);
        return questionObject;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }


    @Override
    public Question remove(Question question) {
        if (!questions.contains(question)) {
            throw new QuestionNotFoundException("Такой вопрос не найден");
        } else {
            questions.remove(question);
            return question;
        }
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        Random rnd = new Random();
        int number = rnd.nextInt(questions.size());
        return questions.stream().collect(Collectors.toList()).get(number);
    }



}

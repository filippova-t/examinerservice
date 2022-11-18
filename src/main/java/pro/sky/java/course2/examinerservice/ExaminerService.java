package pro.sky.java.course2.examinerservice;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}

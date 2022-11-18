package pro.sky.java.course2.examinerservice;

import org.junit.jupiter.api.Test;
import pro.sky.java.course2.examinerservice.exception.QuestionNotFoundException;

import java.util.Random;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class JavaQuestionServiceTest {
    private final JavaQuestionService javaQuestionServiceTest = new JavaQuestionService();
    private final Question expected = new Question("вопрос", "ответ");



    @Test
    void testAddByStrings() {
       assertThat(javaQuestionServiceTest.add("вопрос", "ответ")).isEqualTo(expected);
        assertThat(javaQuestionServiceTest.getAll().size()).isEqualTo(1);
        assertThat(javaQuestionServiceTest.getAll().contains(expected)).isTrue();
        javaQuestionServiceTest.add("вопрос", "ответ");
        assertThat(javaQuestionServiceTest.getAll().size()).isEqualTo(1);
        javaQuestionServiceTest.add("вопрос", "ответ2");
        assertThat(javaQuestionServiceTest.getAll().size()).isEqualTo(2);


    }


    @Test
    void testAddByQuestion() {
        Question actual = new Question("вопрос", "ответ");
        javaQuestionServiceTest.add(actual);
        assertThat(javaQuestionServiceTest.add(actual)).isEqualTo(expected);
        assertThat(javaQuestionServiceTest.getAll().size()).isEqualTo(1);
        assertThat(javaQuestionServiceTest.getAll().contains(expected)).isTrue();

        Question actual2 = new Question("вопрос", "ответ");
        javaQuestionServiceTest.add(actual2);
        assertThat(javaQuestionServiceTest.getAll().size()).isEqualTo(1);
        Question actual3 = new Question("вопрос3", "ответ");
        javaQuestionServiceTest.add(actual3);
        assertThat(javaQuestionServiceTest.getAll().size()).isEqualTo(2);


    }


    @Test
    void testRemove() {
        Question actual = new Question("вопрос", "ответ");
        javaQuestionServiceTest.add(actual);
        assertThat(javaQuestionServiceTest.remove(actual)).isEqualTo(expected);
        assertThat(javaQuestionServiceTest.getAll().size()).isEqualTo(0);
        Question absent = new Question("вопрос1", "ответ1");
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> javaQuestionServiceTest.remove(absent))
                .withMessage("Такой вопрос не найден");

    }


    @Test
    void getAll() {
        javaQuestionServiceTest.add("вопрос1", "ответ1");
        javaQuestionServiceTest.add("вопрос2", "ответ2");
        javaQuestionServiceTest.add("вопрос3", "ответ3");
        javaQuestionServiceTest.getAll();
        assertThat(javaQuestionServiceTest.getAll().size()).isEqualTo(3);
        //assertThat(javaQuestionService.getAll()).isEqualTo();

    }

    @Test
    void getRandomQuestion() {
        Question question1 = javaQuestionServiceTest.add("вопрос1", "ответ1");
        Question question2 = javaQuestionServiceTest.add("вопрос2", "ответ2");
        Question question3 = javaQuestionServiceTest.add("вопрос3", "ответ3");
        Question actual = javaQuestionServiceTest.getRandomQuestion();
        assertThat(javaQuestionServiceTest.getAll().contains(actual)).isTrue();


    }
}
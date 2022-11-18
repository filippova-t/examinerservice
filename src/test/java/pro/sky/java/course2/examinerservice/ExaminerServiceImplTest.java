package pro.sky.java.course2.examinerservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.RequestParam;
import pro.sky.java.course2.examinerservice.exception.MoreQuestionsThanAvailableException;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionServiceTest;

    @InjectMocks
    private ExaminerServiceImpl examinerServiceTest;

    @BeforeEach
    public void createListOfQuestions() {
        Set<Question> questionsForTest = new HashSet<>(List.of(
                new Question("вопрос1", "ответ1"),
                new Question("вопрос2", "ответ2"),
                new Question("вопрос3", "ответ3"),
                new Question("вопрос4", "ответ4"),
                new Question("вопрос5", "ответ5")
        ));
        Mockito.when(questionServiceTest.getAll()).thenReturn(questionsForTest);

    }

    @ParameterizedTest
    @MethodSource("paramsForGetQuestions")
    void testGetQuestions(int amount) {
       if (amount > questionServiceTest.getAll().size()) {
            assertThatExceptionOfType(MoreQuestionsThanAvailableException.class)
                    .isThrownBy(() -> examinerServiceTest.getQuestions(amount))
                    .withMessage("Запрошено больше вопросов, чем есть в базе");
        } else {
           examinerServiceTest.getQuestions(amount);
           assertThat(examinerServiceTest.getQuestions(amount).size()).isEqualTo(amount);

       }
    }


    public static Stream<Arguments> paramsForGetQuestions() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(2),
                Arguments.of(3),
                Arguments.of(4),
                Arguments.of(5),
                Arguments.of(6),
                Arguments.of(10)
        );
    }
}

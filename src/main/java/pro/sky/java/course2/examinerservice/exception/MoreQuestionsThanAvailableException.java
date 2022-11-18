package pro.sky.java.course2.examinerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MoreQuestionsThanAvailableException extends RuntimeException{
    public MoreQuestionsThanAvailableException() {
    }

    public MoreQuestionsThanAvailableException(String message) {
        super(message);
    }


}

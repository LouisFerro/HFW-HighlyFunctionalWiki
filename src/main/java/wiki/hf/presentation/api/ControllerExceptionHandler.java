package wiki.hf.presentation.api;

import wiki.hf.service.exceptions.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpEntity<ProblemDetail> handleNotFoundException(BaseException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
        problemDetail.setProperty("key", exception.getKey());

        return ResponseEntity.of(problemDetail).build();
    }
}
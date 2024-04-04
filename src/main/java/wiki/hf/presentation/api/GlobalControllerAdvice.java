package wiki.hf.presentation.api;

import wiki.hf.service.exceptions.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public HttpEntity<ProblemDetail> handleNoContentException(BaseException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NO_CONTENT, exception.getMessage());
        problemDetail.setProperty("object", exception.getObject());

        return ResponseEntity.of(problemDetail).build();
    }
}
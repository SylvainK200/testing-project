package exceptionhandler.handlers;


import exceptionhandler.exceptions.ExceptionResponse;
import exceptionhandler.exceptions.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ObjectNotFoundException.class})
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<ExceptionResponse> handleException(ObjectNotFoundException exception) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(ExceptionResponse.builder()
                                .message(exception.getMessage())
                                .build());
    }
}

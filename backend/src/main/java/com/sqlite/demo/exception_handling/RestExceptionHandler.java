package com.sqlite.demo.exception_handling;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(JpaSystemException.class)
    protected ResponseEntity<Object> handleJpaSystemException(RuntimeException exception) {
        String message = "Fehler bei Interaktion mit der Datenbank: " + exception.getMessage();
        return buildResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(RuntimeException exception) {
        return buildResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    private ResponseEntity<String> handleException(RuntimeException ex) {
        String bodyOfResponse = "Benötigter Parameter ist nicht vorhanden oder fehlerhaft: " + ex.getCause();
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({JsonProcessingException.class})
    private ResponseEntity<String> handleJsonProcessingException(RuntimeException ex) {
        String bodyOfResponse = "Fehler bei der Verarbeitung der Datenbank-Antwort: " + ex.getCause();
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> buildResponseEntity(String message, HttpStatus status) {
        return new ResponseEntity<>(message, status);
    }

}

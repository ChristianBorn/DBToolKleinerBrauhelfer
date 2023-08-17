package com.sqlite.demo.exception_handling;


import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Objects;


@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(JpaSystemException.class)
    protected ResponseEntity<Object> handleJpaSystemException(WebRequest request) {
        String message = "Menge zu füllender Gebinde übersteigt Gesamtzahl der verfügbaren Gebinde: "
                + Objects.requireNonNull(request.getParameterValues("number"))[0];
        return buildResponseEntity(message, HttpStatus.BAD_REQUEST);
    }
    private ResponseEntity<Object> buildResponseEntity(String message, HttpStatus status) {
        return new ResponseEntity<>(message, status);
    }
}

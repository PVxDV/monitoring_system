package com.pvxdv.metrics_producer.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ExceptionApiHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidExceptionResolve(MethodArgumentNotValidException e){
        String validationErrorMassage = "Validation error. " + e.getBindingResult().getFieldErrors().stream()
                .map(error -> "%s:%s".formatted(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.joining(", "));
        log.error(validationErrorMassage);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(new Date(), validationErrorMassage));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> otherExceptionResolve(Exception e){
        log.error(e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessage(new Date(),e.getMessage()));
    }
}

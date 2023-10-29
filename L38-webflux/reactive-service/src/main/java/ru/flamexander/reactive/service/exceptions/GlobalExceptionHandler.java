package ru.flamexander.reactive.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorDto> handleAppException(AppException e) {
        return new ResponseEntity<>(
                new ErrorDto(e.getCode()), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}

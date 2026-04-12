package com.chinmay.expense_tracker.exceptions;


import com.chinmay.expense_tracker.dto.error.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(
                new ErrorResponseDto(
                        HttpStatus.NOT_FOUND.value(),
                        e.getMessage(),
                        System.currentTimeMillis(),
                        Map.of()
                ), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(MethodArgumentNotValidException ex) {
        final Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(new ErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                System.currentTimeMillis(),
                errors
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsWithEmail.class)
    public ResponseEntity<ErrorResponseDto> handleUserExistsWithSameEmail(UserAlreadyExistsWithEmail ex) {
        return new ResponseEntity<>(
                new ErrorResponseDto(
                        HttpStatus.CONFLICT.value(),
                        ex.getMessage(),
                        System.currentTimeMillis(),
                        Map.of()
                ), HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleExpenseNotFoundException(ExpenseNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponseDto(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage(),
                        System.currentTimeMillis(),
                        Map.of()
                ), HttpStatus.NOT_FOUND
        );
    }

}

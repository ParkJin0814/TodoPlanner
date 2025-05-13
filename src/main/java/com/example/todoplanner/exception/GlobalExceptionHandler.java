package com.example.todoplanner.exception;

import com.example.todoplanner.dto.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PlannerException.class)
    public ResponseEntity<ErrorResponseDto> handlePlannerException(PlannerException e) {
        ErrorResponseDto response = new ErrorResponseDto(e.getStatus(), e.getMessage());
        return new ResponseEntity<>(response, e.getStatus());
    }

    // 비밀번호가 틀릴때
    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ErrorResponseDto> handlePasswordMismatchException(PasswordMismatchException e) {
        ErrorResponseDto response = new ErrorResponseDto(e.getStatus(), e.getMessage());
        return new ResponseEntity<>(response, e.getStatus());
    }
    
    // content 내용이 비어있을때
    @ExceptionHandler(PlanContentNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlePlanContentNotFoundException(PlanContentNotFoundException e) {
        ErrorResponseDto response = new ErrorResponseDto(e.getStatus(), e.getMessage());
        return new ResponseEntity<>(response, e.getStatus());
    }

    // 플랜아이디를 찾을수 없을 때
    @ExceptionHandler(PlanNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlePlanNotFoundException(PlanNotFoundException e) {
        ErrorResponseDto response = new ErrorResponseDto(e.getStatus(), e.getMessage());
        return new ResponseEntity<>(response, e.getStatus());
    }
}

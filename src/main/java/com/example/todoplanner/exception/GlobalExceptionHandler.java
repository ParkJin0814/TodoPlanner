package com.example.todoplanner.exception;

import com.example.todoplanner.dto.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 출처 : https://jyami.tistory.com/55  아직 이 매서드 이해 불가 추후 다시 봐야함
    // ValidException에 대한 에러를 처리하는거로 확인됨
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

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

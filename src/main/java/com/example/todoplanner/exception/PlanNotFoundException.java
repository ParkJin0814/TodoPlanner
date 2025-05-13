package com.example.todoplanner.exception;

import org.springframework.http.HttpStatus;

public class PlanNotFoundException extends PlannerException {
    public PlanNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "Id에 맞는 플랜을 찾을수 없습니다 = " + id);
    }
}

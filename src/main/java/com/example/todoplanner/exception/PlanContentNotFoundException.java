package com.example.todoplanner.exception;

import org.springframework.http.HttpStatus;

public class PlanContentNotFoundException extends PlannerException {
    public PlanContentNotFoundException() {
        super(HttpStatus.NOT_FOUND, "입력값을 찾을수 없습니다");
    }
}

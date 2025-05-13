package com.example.todoplanner.exception;

import org.springframework.http.HttpStatus;

public class PasswordMismatchException extends PlannerException {
    public PasswordMismatchException() {
        super(HttpStatus.UNAUTHORIZED, "비밀번호가 맞지 않습니다.");
    }
}

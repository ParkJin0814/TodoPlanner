package com.example.todoplanner.exception;

import org.springframework.http.HttpStatus;

public class PasswordMismatchException extends PlannerException {
    public PasswordMismatchException() {
        super(HttpStatus.UNAUTHORIZED, "The password is incorrect.");
    }
}

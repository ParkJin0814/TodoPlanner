package com.example.todoplanner.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PlannerException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public PlannerException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}



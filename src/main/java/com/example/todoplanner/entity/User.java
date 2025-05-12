package com.example.todoplanner.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class User {
    private Long id;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}

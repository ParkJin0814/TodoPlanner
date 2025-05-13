package com.example.todoplanner.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }
}

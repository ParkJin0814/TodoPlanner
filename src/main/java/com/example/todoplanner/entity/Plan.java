package com.example.todoplanner.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Plan {
    private Long id;
    private Long userId;
    private String password;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Plan(Long userId, String password, String title, String content) {
        this.userId = userId;
        this.password = password;
        this.title = title;
        this.content = content;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }
}

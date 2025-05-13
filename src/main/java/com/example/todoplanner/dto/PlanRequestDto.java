package com.example.todoplanner.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlanRequestDto {
    private Long userId;
    private String password;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}

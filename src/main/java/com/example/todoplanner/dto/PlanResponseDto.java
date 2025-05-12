package com.example.todoplanner.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PlanResponseDto {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}

package com.example.todoplanner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlanRequestDto {
    private Long userId;
    @NotBlank(message = "비밀번호는 필수 입력값입니다")
    @Size(max = 20, message = "비밀번호는 20자 이내로 입력해주세요")
    private String password;
    @NotBlank(message = "제목은 필수 입력값입니다")
    private String title;
    @NotBlank(message = "내용은 필수 입력값입니다")
    @Size(max = 200, message = "할일은 200자 이내로 입력해주세요")
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}

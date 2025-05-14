package com.example.todoplanner.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserCreateRequestDto {
    @NotBlank(message = "이름은 필수 입력값입니다")
    @Size(max = 20, message = "이름은 20자 이내로 입력해주세요")
    private String name;
    @NotBlank(message = "이메일은 필수 입력값입니다")
    @Email(message = "올바른 이메일 형식이 아닙니다")
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}

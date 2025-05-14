package com.example.todoplanner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdateRequestDto {
    @NotBlank(message = "이름은 필수 입력값입니다")
    @Size(max = 20, message = "이름은 20자 이내로 입력해주세요")
    private String name;
}

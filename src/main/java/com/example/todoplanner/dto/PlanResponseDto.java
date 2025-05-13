package com.example.todoplanner.dto;

import com.example.todoplanner.entity.Plan;
import com.example.todoplanner.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PlanResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private UserResponseDto userResponseDto;

    public PlanResponseDto(Plan plan, UserResponseDto dto) {
        this.id = plan.getId();
        this.title = plan.getTitle();
        this.content = plan.getContent();
        this.createAt = plan.getCreateAt();
        this.updateAt = plan.getCreateAt();

        this.userResponseDto = dto;
    }
}

package com.example.todoplanner.dto;

import com.example.todoplanner.entity.Plan;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public class PageResponseDto {
    private List<PlanResponseDto> planList;
    private int totalPages;
    private long totalPlanSize;
    private int currentPage;
    private int pageSize;

    public PageResponseDto(List<PlanResponseDto> planList, PageRequestDto dto, Integer totalPlanSize) {
        this.planList = planList;
        this.totalPages = (int) Math.ceil((double) totalPlanSize / dto.getSize());
        this.totalPlanSize = planList.size();
        this.currentPage = dto.getPage();
        this.pageSize = dto.getSize();
    }
}
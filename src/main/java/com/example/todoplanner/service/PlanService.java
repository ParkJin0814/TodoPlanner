package com.example.todoplanner.service;

import com.example.todoplanner.dto.PageRequestDto;
import com.example.todoplanner.dto.PageResponseDto;
import com.example.todoplanner.dto.PlanRequestDto;
import com.example.todoplanner.dto.PlanResponseDto;

import java.time.LocalDate;

public interface PlanService {
    PlanResponseDto savePlan(PlanRequestDto dto);

    PageResponseDto findAllPlans(PageRequestDto dto);

    PlanResponseDto findPlanById(Long id);

    PlanResponseDto updatePlanContent(Long id, String password, String content);

    void deletePlan(Long id, String password);

    PageResponseDto findPlanListByUserId(Long userId, PageRequestDto dto);

    PageResponseDto findPlanListByUpdateAt(LocalDate updateAt, PageRequestDto dto);
}

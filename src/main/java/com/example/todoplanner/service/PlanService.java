package com.example.todoplanner.service;

import com.example.todoplanner.dto.PlanRequestDto;
import com.example.todoplanner.dto.PlanResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface PlanService {
    PlanResponseDto savePlan(PlanRequestDto dto);

    List<PlanResponseDto> findAllPlans();

    PlanResponseDto findPlanById(Long id);

    PlanResponseDto updatePlanContent(Long id, String password, String content);

    void deletePlan(Long id, String password);

    List<PlanResponseDto> findPlanListUserByName(String name);

    List<PlanResponseDto> findPlanListUserByUpdateAt(LocalDate updateAt);
}

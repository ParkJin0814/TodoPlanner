package com.example.todoplanner.repository;

import com.example.todoplanner.dto.PageRequestDto;
import com.example.todoplanner.dto.PageResponseDto;
import com.example.todoplanner.dto.PlanResponseDto;
import com.example.todoplanner.entity.Plan;

import java.time.LocalDate;

public interface PlanRepository {
    PlanResponseDto savePlan(Plan plan);

    PageResponseDto findAllPlans(PageRequestDto dto);

    Plan findPlanByIdOrElseThrow(Long id);

    void updatePlanContent(Long id, String contents);

    void deletePlan(Long id);

    PageResponseDto findPlanListByUserId(Long userId, PageRequestDto dto);

    PageResponseDto findPlanListByUpdateAt(LocalDate updateAt, PageRequestDto dto);

    PageResponseDto findPlanListByUserIdAndUpdateAt(Long userId, LocalDate updateAt, PageRequestDto dto);
}

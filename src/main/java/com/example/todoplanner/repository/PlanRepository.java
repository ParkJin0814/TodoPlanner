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

    PageResponseDto findPlanListUserByName(String name, PageRequestDto dto);

    PageResponseDto findPlanListUserByUpdateAt(LocalDate updateAt, PageRequestDto dto);
}

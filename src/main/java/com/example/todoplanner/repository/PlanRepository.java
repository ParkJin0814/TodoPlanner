package com.example.todoplanner.repository;

import com.example.todoplanner.dto.PlanResponseDto;
import com.example.todoplanner.entity.Plan;

import java.util.List;

public interface PlanRepository {
    PlanResponseDto savePlan(Plan plan);

    List<PlanResponseDto> findAllPlans();

    Plan findPlanByIdOrElseThrow(Long id);

    void updatePlanContent(Long id, String contents);

    void deletePlan(Long id);

    List<PlanResponseDto> findPlanListUserByName(String name);
}

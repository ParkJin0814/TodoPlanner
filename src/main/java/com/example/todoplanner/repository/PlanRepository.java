package com.example.todoplanner.repository;

import com.example.todoplanner.dto.PlanResponseDto;
import com.example.todoplanner.entity.Plan;

public interface PlanRepository {
    PlanResponseDto savePlan(Plan plan);
}

package com.example.todoplanner.service;

import com.example.todoplanner.dto.PlanRequestDto;
import com.example.todoplanner.dto.PlanResponseDto;

public interface PlanService {
    PlanResponseDto savePlan(PlanRequestDto dto);
}

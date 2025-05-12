package com.example.todoplanner.service;

import com.example.todoplanner.dto.PlanRequestDto;
import com.example.todoplanner.dto.PlanResponseDto;
import com.example.todoplanner.entity.Plan;
import com.example.todoplanner.repository.PlanRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PlanServiceImpl implements PlanService{
    private final PlanRepository planRepository;

    public PlanServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public PlanResponseDto savePlan(PlanRequestDto dto) {
        // 요청받은 데이터로 객체생성
        Plan plan = new Plan(dto.getUserId(), dto.getTitle(), dto.getContent());

        return planRepository.savePlan(plan);
    }
}

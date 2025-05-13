package com.example.todoplanner.service;

import com.example.todoplanner.dto.PlanRequestDto;
import com.example.todoplanner.dto.PlanResponseDto;
import com.example.todoplanner.entity.Plan;
import com.example.todoplanner.repository.PlanRepository;
import com.example.todoplanner.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlanServiceImpl implements PlanService{
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    public PlanServiceImpl(PlanRepository planRepository, UserRepository userRepository) {
        this.planRepository = planRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PlanResponseDto savePlan(PlanRequestDto dto) {
        // 요청받은 데이터로 객체생성
        Plan plan = new Plan(dto.getUserId(), dto.getTitle(), dto.getContent());

        return planRepository.savePlan(plan);
    }

    @Override
    public List<PlanResponseDto> findAllPlans() {
        return planRepository.findAllPlans();
    }

    @Override
    public PlanResponseDto findPlanById(Long id) {
        Plan plan = planRepository.findPlanByIdOrElseThrow(id);
        String name = userRepository.findUserByIdOrElseThrow(plan.getUserId()).getName();

        return new PlanResponseDto(plan, name);
    }
}

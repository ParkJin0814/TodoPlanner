package com.example.todoplanner.service;

import com.example.todoplanner.dto.*;
import com.example.todoplanner.entity.Plan;
import com.example.todoplanner.entity.User;
import com.example.todoplanner.exception.PasswordMismatchException;
import com.example.todoplanner.exception.PlanContentNotFoundException;
import com.example.todoplanner.repository.PlanRepository;
import com.example.todoplanner.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    public PlanServiceImpl(PlanRepository planRepository, UserRepository userRepository) {
        this.planRepository = planRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PlanResponseDto savePlan(PlanRequestDto dto) {
        // 요청받은 데이터로 객체생성
        Plan plan = new Plan(dto.getUserId(), dto.getPassword(), dto.getTitle(), dto.getContent());

        return planRepository.savePlan(plan);
    }

    @Override
    public PageResponseDto findAllPlans(PageRequestDto dto) {
        return planRepository.findAllPlans(dto);
    }

    @Override
    public PlanResponseDto findPlanById(Long id) {
        Plan plan = planRepository.findPlanByIdOrElseThrow(id);

        return new PlanResponseDto(plan, new UserResponseDto(userRepository.findUserByIdOrElseThrow(plan.getUserId())));
    }

    @Override
    public PageResponseDto findPlanListByUserId(Long userId, PageRequestDto dto) {
        return planRepository.findPlanListByUserId(userId, dto);
    }

    @Override
    public PageResponseDto findPlanListByUpdateAt(LocalDate updateAt, PageRequestDto dto) {
        return planRepository.findPlanListByUpdateAt(updateAt, dto);
    }

    @Override
    public PlanResponseDto updatePlanContent(Long id, String password, String content) {
        Plan plan = planRepository.findPlanByIdOrElseThrow(id);
        User user = userRepository.findUserByIdOrElseThrow(plan.getUserId());
        // 비밀번호 검증
        if (!password.equals(plan.getPassword())) {
            throw new PasswordMismatchException();
        }

        if (content == null){
            throw new PlanContentNotFoundException();
        }

        planRepository.updatePlanContent(id, content);

        return new PlanResponseDto(planRepository.findPlanByIdOrElseThrow(id), new UserResponseDto(user));
    }

    @Override
    public void deletePlan(Long id, String password) {
        Plan plan = planRepository.findPlanByIdOrElseThrow(id);

        if (!password.equals(plan.getPassword())) {
            throw new PasswordMismatchException();
        }

        planRepository.deletePlan(id);
    }


}

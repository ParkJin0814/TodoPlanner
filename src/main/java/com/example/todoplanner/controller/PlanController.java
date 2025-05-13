package com.example.todoplanner.controller;

import com.example.todoplanner.dto.PlanRequestDto;
import com.example.todoplanner.dto.PlanResponseDto;
import com.example.todoplanner.service.PlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planner")
public class PlanController {
    private final PlanService planService;

    // 생성자
    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    // 일정생성
    @PostMapping
    public ResponseEntity<PlanResponseDto> createPlan(@RequestBody PlanRequestDto dto) {
        return new ResponseEntity<>(planService.savePlan(dto), HttpStatus.CREATED);
    }

    // 전체조회
    @GetMapping
    public List<PlanResponseDto> findAllPlans() {
        return planService.findAllPlans();
    }

    // 고유식별자로 단건조회
    @GetMapping("/{id}")
    public ResponseEntity<PlanResponseDto> findPlanById(@PathVariable Long id) {
        return new ResponseEntity<>(planService.findPlanById(id), HttpStatus.OK);
    }

    // 내용수정
    @PutMapping("/{id}")
    public ResponseEntity<PlanResponseDto> updateContentPlanById(
            @PathVariable Long id,
            @RequestBody PlanRequestDto dto
    ) {
        return new ResponseEntity<>(planService.updatePlanContent(id, dto.getPassword(), dto.getContent()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(
            @PathVariable Long id,
            @RequestParam String password
    ) {
        planService.deletePlan(id, password);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

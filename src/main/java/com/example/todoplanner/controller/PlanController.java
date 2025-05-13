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
    public ResponseEntity<List<PlanResponseDto>> findAllPlans() {
        return new ResponseEntity<>(planService.findAllPlans(), HttpStatus.OK);
    }

    // 고유식별자로 단건조회
    @GetMapping("/{id}")
    public ResponseEntity<PlanResponseDto> findPlanById(@PathVariable Long id) {
        return new ResponseEntity<>(planService.findPlanById(id), HttpStatus.OK);
    }

    // 작성자 조회
    @GetMapping("/search")
    public ResponseEntity<List<PlanResponseDto>> findPlanListUserByName(@RequestParam String name) {
        return new ResponseEntity<>(planService.findPlanListUserByName(name), HttpStatus.OK);
    }


    // 수정일 조회


    // 내용수정
    @PutMapping("/{id}")
    public ResponseEntity<PlanResponseDto> updateContentPlanById(
            @PathVariable Long id,
            @RequestBody PlanRequestDto dto
    ) {
        return new ResponseEntity<>(planService.updatePlanContent(id, dto.getPassword(), dto.getContent()), HttpStatus.OK);
    }

    // 일정삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(
            @PathVariable Long id,
            @RequestParam String password
    ) {
        planService.deletePlan(id, password);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

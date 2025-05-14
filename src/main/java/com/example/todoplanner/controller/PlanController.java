package com.example.todoplanner.controller;

import com.example.todoplanner.dto.*;
import com.example.todoplanner.service.PlanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;

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
    public ResponseEntity<PlanResponseDto> createPlan(@Valid @RequestBody PlanRequestDto dto) {
        return new ResponseEntity<>(planService.savePlan(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageResponseDto> findAllPlans(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequestDto dto = new PageRequestDto(page, size);
        return new ResponseEntity<>(planService.findAllPlans(dto), HttpStatus.OK);
    }

    // 고유식별자로 단건조회
    @GetMapping("/{id}")
    public ResponseEntity<PlanResponseDto> findPlanById(@PathVariable Long id) {
        return new ResponseEntity<>(planService.findPlanById(id), HttpStatus.OK);
    }

    // 작성자, 수정일 조회
    @GetMapping("/search")
    public ResponseEntity<PageResponseDto> searchPlans(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) LocalDate updateAt,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageRequestDto dto = new PageRequestDto(page, size);
        if (userId != null && updateAt != null) {
            return ResponseEntity.ok(planService.findPlanListByUserIdAndUpdateAt(userId, updateAt, dto));
        }
        else if (userId != null) {
            return ResponseEntity.ok(planService.findPlanListByUserId(userId, dto));
        } else if (updateAt != null) {
            return ResponseEntity.ok(planService.findPlanListByUpdateAt(updateAt, dto));
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please search for the name or updateAt");
    }



    // 내용수정
    @PutMapping("/{id}")
    public ResponseEntity<PlanResponseDto> updateContentPlanById(
            @PathVariable Long id,
            @Valid @RequestBody PlanUpdateRequestDto dto
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
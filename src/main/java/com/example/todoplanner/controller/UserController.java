package com.example.todoplanner.controller;

import com.example.todoplanner.dto.*;
import com.example.todoplanner.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    //생성자
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 유저생성
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserCreateRequestDto dto) {
        return new ResponseEntity<>(userService.saveUser(dto), HttpStatus.CREATED);
    }

    // 유저조회
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllUser() {
        return new ResponseEntity<>(userService.findAllUser(), HttpStatus.OK);
    }

    // 유저단건조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }


    // 유저수정
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUserName(
            @PathVariable Long id,
            @RequestBody UserUpdateRequestDto dto
    ) {
        return new ResponseEntity<>(userService.updateUserName(id, dto.getName()), HttpStatus.OK);
    }


    // 유저삭제

}

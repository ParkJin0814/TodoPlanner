package com.example.todoplanner.service;

import com.example.todoplanner.dto.UserCreateRequestDto;
import com.example.todoplanner.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto saveUser(UserCreateRequestDto dto);

    List<UserResponseDto> findAllUser();

    UserResponseDto findUserById(Long id);

    UserResponseDto updateUserName(Long id, String name);
}

package com.example.todoplanner.service;

import com.example.todoplanner.dto.UserRequestDto;
import com.example.todoplanner.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto saveUser(UserRequestDto dto);

    List<UserResponseDto> findAllUser();

    UserResponseDto findUserById(Long id);
}

package com.example.todoplanner.service;

import com.example.todoplanner.dto.UserRequestDto;
import com.example.todoplanner.dto.UserResponseDto;

public interface UserService {
    UserResponseDto saveUser(UserRequestDto dto);
}

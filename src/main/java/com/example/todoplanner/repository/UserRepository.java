package com.example.todoplanner.repository;

import com.example.todoplanner.dto.UserResponseDto;
import com.example.todoplanner.entity.User;


public interface UserRepository {
    UserResponseDto saveUser(User user);
    User userPlanByIdOrElseThrow(Long id);
}

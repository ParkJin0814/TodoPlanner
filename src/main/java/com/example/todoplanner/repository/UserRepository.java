package com.example.todoplanner.repository;

import com.example.todoplanner.dto.UserResponseDto;
import com.example.todoplanner.entity.User;

import java.util.List;


public interface UserRepository {
    UserResponseDto saveUser(User user);
    User findUserByIdOrElseThrow(Long id);

    List<UserResponseDto> findAllUser();
}

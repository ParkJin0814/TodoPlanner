package com.example.todoplanner.service;

import com.example.todoplanner.dto.UserRequestDto;
import com.example.todoplanner.dto.UserResponseDto;
import com.example.todoplanner.entity.User;
import com.example.todoplanner.repository.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto saveUser(UserRequestDto dto) {
        User user = new User(dto.getName(), dto.getEmail());

        return userRepository.saveUser(user);
    }
}

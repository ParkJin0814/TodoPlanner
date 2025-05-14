package com.example.todoplanner.service;

import com.example.todoplanner.dto.UserCreateRequestDto;
import com.example.todoplanner.dto.UserResponseDto;
import com.example.todoplanner.entity.User;
import com.example.todoplanner.exception.PlanContentNotFoundException;
import com.example.todoplanner.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto saveUser(UserCreateRequestDto dto) {
        User user = new User(dto.getName(), dto.getEmail());

        return userRepository.saveUser(user);
    }

    @Override
    public List<UserResponseDto> findAllUser() {
        return userRepository.findAllUser();
    }

    @Override
    public UserResponseDto findUserById(Long id) {
        return new UserResponseDto(userRepository.findUserByIdOrElseThrow(id));
    }

    @Override
    public UserResponseDto updateUserName(Long id, String name) {
        if (name == null){
            throw new PlanContentNotFoundException();
        }

        userRepository.updateUserName(id, name);

        return new UserResponseDto(userRepository.findUserByIdOrElseThrow(id));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }
}

package com.example.demo.service;

import com.example.demo.controller.user.dto.UserCreateRequestDto;
import com.example.demo.controller.user.dto.UserResponseDto;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.repository.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto save(UserCreateRequestDto request) {
        User user = User.create(
                request.getLoginId(),
                request.getPassword(),
                request.getName()
        );
        User created = userRepository.save(user);
        return UserResponseDto.from(created);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }


}

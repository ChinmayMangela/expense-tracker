package com.chinmay.expense_tracker.service;

import com.chinmay.expense_tracker.config.SecurityConfig;
import com.chinmay.expense_tracker.domain.entity.UserEntity;
import com.chinmay.expense_tracker.dto.user.CreateUserRequest;
import com.chinmay.expense_tracker.dto.user.UserResponse;
import com.chinmay.expense_tracker.mapper.UserMapper;
import com.chinmay.expense_tracker.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public UserResponse createUser(CreateUserRequest createUserRequest) {
        final UserEntity userEntity = new UserEntity();
        userEntity.setName(createUserRequest.name());
        userEntity.setEmail(createUserRequest.email());
        userEntity.setPasswordHash(passwordEncoder.encode(createUserRequest.password()));
        final UserEntity user = userRepository.save(userEntity);
        return UserMapper.toResponse(user);
    }

}

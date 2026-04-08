package com.chinmay.expense_tracker.service;

import com.chinmay.expense_tracker.config.SecurityConfig;
import com.chinmay.expense_tracker.domain.entity.UserEntity;
import com.chinmay.expense_tracker.dto.user.CreateUserRequest;
import com.chinmay.expense_tracker.dto.user.UserResponse;
import com.chinmay.expense_tracker.mapper.UserMapper;
import com.chinmay.expense_tracker.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }




}

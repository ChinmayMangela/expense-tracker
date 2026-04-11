package com.chinmay.expense_tracker.service;

import com.chinmay.expense_tracker.domain.entity.UserEntity;
import com.chinmay.expense_tracker.dto.user.CreateUserRequest;
import com.chinmay.expense_tracker.dto.user.UserResponse;
import com.chinmay.expense_tracker.exceptions.UserAlreadyExistsWithEmail;
import com.chinmay.expense_tracker.exceptions.UserNotFoundException;
import com.chinmay.expense_tracker.mapper.UserMapper;
import com.chinmay.expense_tracker.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public UserResponse createUser(CreateUserRequest createUserRequest) {
        userRepository.findByEmail(createUserRequest.email())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsWithEmail(user.getEmail());
                });
        final UserEntity userEntity = new UserEntity();
        userEntity.setName(createUserRequest.name());
        userEntity.setEmail(createUserRequest.email());
        userEntity.setPasswordHash(passwordEncoder.encode(createUserRequest.password()));
        final UserEntity user = userRepository.save(userEntity);
        return UserMapper.toResponse(user);
    }

    public UserResponse fetchUserById(UUID id) {
        final UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return UserMapper.toResponse(user);
    }

    public List<UserResponse> fetchAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }


}

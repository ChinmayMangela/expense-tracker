package com.chinmay.expense_tracker.controller;


import com.chinmay.expense_tracker.dto.user.CreateUserRequest;
import com.chinmay.expense_tracker.dto.user.UserResponse;
import com.chinmay.expense_tracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody CreateUserRequest createUserRequest
    ) {
        return new ResponseEntity<>(userService.createUser(createUserRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> fetchUserById(
            @PathVariable UUID id
            ) {
        return new ResponseEntity<>(userService.fetchUserById(id), HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> fetchAllUsers() {
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.FOUND);
    }
}

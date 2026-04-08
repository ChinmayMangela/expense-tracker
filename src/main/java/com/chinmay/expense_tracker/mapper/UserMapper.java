package com.chinmay.expense_tracker.mapper;

import com.chinmay.expense_tracker.domain.entity.UserEntity;
import com.chinmay.expense_tracker.dto.user.UserResponse;

public class UserMapper {

    public static UserResponse toResponse(UserEntity entity) {
        return new UserResponse(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getExpenses().stream().map(ExpenseMapper::toResponse).toList()
        );
    }
}

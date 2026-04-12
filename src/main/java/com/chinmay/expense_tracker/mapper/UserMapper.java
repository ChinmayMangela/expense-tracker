package com.chinmay.expense_tracker.mapper;

import com.chinmay.expense_tracker.domain.entity.ExpenseEntity;
import com.chinmay.expense_tracker.domain.entity.UserEntity;
import com.chinmay.expense_tracker.dto.user.UserResponse;

import java.util.List;

public class UserMapper {

    public static UserResponse toResponse(UserEntity entity) {
        if (entity == null) return null;
        return new UserResponse(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPasswordHash(),
                entity.getExpenses() != null ? entity.getExpenses().stream().map(ExpenseMapper::toResponse).toList() : List.of()
        );
    }

    public static UserEntity toEntity(UserResponse userResponse) {
        if (userResponse == null) return null;
        final UserEntity entity = new UserEntity();
        entity.setId(userResponse.id());
        entity.setName(userResponse.name());
        entity.setEmail(userResponse.email());
        entity.setPasswordHash(userResponse.passwordHash());

        if(entity.getExpenses() != null) {
            List<ExpenseEntity> expenseEntities = userResponse.expenses()
                    .stream().map(
                            expenseResponse ->
                                 ExpenseMapper.toEntity(expenseResponse, entity)
                    ).toList();

            entity.setExpenses(expenseEntities);
        }

        return entity;
    }
}

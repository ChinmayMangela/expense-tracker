package com.chinmay.expense_tracker.mapper;

import com.chinmay.expense_tracker.domain.entity.ExpenseEntity;
import com.chinmay.expense_tracker.domain.entity.UserEntity;
import com.chinmay.expense_tracker.dto.expense.ExpenseResponse;

public class ExpenseMapper {

    public static ExpenseResponse toResponse(ExpenseEntity entity) {
        return new ExpenseResponse(
                entity.getId(),
                entity.getPaymentDescription(),
                entity.getAmount(),
                entity.getDate(),
                entity.getUser().getId(), entity.getPaymentMethod(), entity.getCategory()
                );
    }

    public static ExpenseEntity toEntity(ExpenseResponse response, UserEntity user) {
        return  new ExpenseEntity(
                response.id(),
                response.paymentDescription(),
                response.amount(),
                response.date(),
                user,
                response.paymentMethod(),
                response.category()
        );
    }
}

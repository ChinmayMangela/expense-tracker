package com.chinmay.expense_tracker.mapper;

import com.chinmay.expense_tracker.domain.entity.ExpenseEntity;
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
}

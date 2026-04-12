package com.chinmay.expense_tracker.dto.user;

import com.chinmay.expense_tracker.dto.expense.ExpenseResponse;

import java.util.List;
import java.util.UUID;

public record UserResponse (
         UUID id,
         String name,
         String email,
         String passwordHash,
         List<ExpenseResponse> expenses
) {

}

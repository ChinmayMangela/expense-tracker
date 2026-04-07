package com.chinmay.expense_tracker.dto.expense;

import com.chinmay.expense_tracker.domain.entity.ExpenseCategory;
import com.chinmay.expense_tracker.domain.entity.PaymentMethod;
import com.chinmay.expense_tracker.domain.entity.UserEntity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record ExpenseResponse(
        UUID id,
        String paymentDescription,
        BigDecimal amount,
        Instant date,
        UUID userId,
        PaymentMethod paymentMethod,
        ExpenseCategory category
) {
}

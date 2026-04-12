package com.chinmay.expense_tracker.dto.expense;

import com.chinmay.expense_tracker.domain.entity.ExpenseCategory;
import com.chinmay.expense_tracker.domain.entity.PaymentMethod;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record PatchExpenseRequest(

        @Size(max = 1000, message = DESCRIPTION_SIZE_ERROR_MESSAGE)
        String paymentDescription,

        @DecimalMin(value = "0.0")
        BigDecimal amount,

        PaymentMethod paymentMethod,

        ExpenseCategory category
) {
    private static final String DESCRIPTION_SIZE_ERROR_MESSAGE = "Payment description should be at most 1000 characters long";
}

package com.chinmay.expense_tracker.dto.expense;

import com.chinmay.expense_tracker.domain.entity.ExpenseCategory;
import com.chinmay.expense_tracker.domain.entity.PaymentMethod;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateExpenseRequest(
        @Size(max = 1000, message = DESCRIPTION_SIZE_ERROR_MESSAGE)
        @NotBlank(message = EMPTY_DESCRIPTION_ERROR_MESSAGE)
        String paymentDescription,

        @NotNull(message = EMPTY_AMOUNT_ERROR_MESSAGE)
        @DecimalMin(value = "0.0")
        BigDecimal amount,

        @NotNull(message = NULL_USER_ID_ERROR_MESSAGE)
        UUID userId,

        @NotNull(message = EMPTY_PAYMENT_METHOD_ERROR_MESSAGE)
        PaymentMethod paymentMethod,

        @NotNull(message = EMPTY_CATEGORY_ERROR_MESSAGE)
        ExpenseCategory category
) {

    private static final String EMPTY_DESCRIPTION_ERROR_MESSAGE = "Payment description is required";
    private static final String DESCRIPTION_SIZE_ERROR_MESSAGE = "Payment description should be at most 1000 characters long";
    private static final String EMPTY_AMOUNT_ERROR_MESSAGE = "Amount is required";
    private static final String EMPTY_PAYMENT_METHOD_ERROR_MESSAGE = "Payment method is required";
    private static final String EMPTY_CATEGORY_ERROR_MESSAGE = "Expense category is required";
    private static final String NULL_USER_ID_ERROR_MESSAGE = "User id is required";

}

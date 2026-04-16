package com.chinmay.expense_tracker.dto.expense;

import com.chinmay.expense_tracker.domain.entity.ExpenseCategory;

import java.math.BigDecimal;
import java.util.Map;

public record ExpenseSummaryResponse(
        BigDecimal totalSpent,
        Map<ExpenseCategory, BigDecimal> categoryBreakdown
        ) {

}

package com.chinmay.expense_tracker.controller;


import com.chinmay.expense_tracker.dto.expense.CreateExpenseRequest;
import com.chinmay.expense_tracker.dto.expense.ExpenseResponse;
import com.chinmay.expense_tracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> createResponse(
            @Valid @RequestBody CreateExpenseRequest createExpenseRequest
    ) {
        return ResponseEntity.ok(expenseService.createExpense(createExpenseRequest));
    }
}

package com.chinmay.expense_tracker.controller;


import com.chinmay.expense_tracker.dto.expense.CreateExpenseRequest;
import com.chinmay.expense_tracker.dto.expense.ExpenseResponse;
import com.chinmay.expense_tracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponse> fetchExpenseById(
            @PathVariable UUID id
            ) {
        return ResponseEntity.ok(expenseService.fetchExpenseById(id));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> fetchAllExpenses() {
        return ResponseEntity.ok(expenseService.fetchAllExpenses());
    }
}

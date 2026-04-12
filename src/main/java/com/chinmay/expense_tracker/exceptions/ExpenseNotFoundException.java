package com.chinmay.expense_tracker.exceptions;

import java.util.UUID;

public class ExpenseNotFoundException extends RuntimeException {

    public ExpenseNotFoundException(UUID id) {
        super("Expense with ID: " + id + " not found");
    }
}

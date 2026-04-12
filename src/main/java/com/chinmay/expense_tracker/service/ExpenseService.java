package com.chinmay.expense_tracker.service;


import com.chinmay.expense_tracker.domain.entity.ExpenseEntity;
import com.chinmay.expense_tracker.dto.expense.CreateExpenseRequest;
import com.chinmay.expense_tracker.dto.expense.ExpenseResponse;
import com.chinmay.expense_tracker.exceptions.ExpenseNotFoundException;
import com.chinmay.expense_tracker.mapper.ExpenseMapper;
import com.chinmay.expense_tracker.mapper.UserMapper;
import com.chinmay.expense_tracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserService userService;

    public ExpenseService(ExpenseRepository expenseRepository, UserService userService) {
        this.expenseRepository = expenseRepository;
        this.userService = userService;
    }


    public ExpenseResponse createExpense(
            CreateExpenseRequest createExpenseRequest
    ) {
        final ExpenseEntity expenseEntity = new ExpenseEntity();
        expenseEntity.setPaymentDescription(createExpenseRequest.paymentDescription());
        expenseEntity.setAmount(createExpenseRequest.amount());
        expenseEntity.setUser(
                UserMapper.toEntity(userService.fetchUserById(createExpenseRequest.userId()))
        );
        expenseEntity.setPaymentMethod(createExpenseRequest.paymentMethod());
        expenseEntity.setCategory(createExpenseRequest.category());
        expenseRepository.save(expenseEntity);
        return ExpenseMapper.toResponse(expenseEntity);
    }

    public ExpenseResponse fetchExpenseById(
            UUID id
    ) {
        final ExpenseEntity expense = expenseRepository
                .findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException(id));

        return ExpenseMapper.toResponse(expense);

    }
}

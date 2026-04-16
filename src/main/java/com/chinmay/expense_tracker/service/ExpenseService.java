package com.chinmay.expense_tracker.service;


import com.chinmay.expense_tracker.controller.ExpenseController;
import com.chinmay.expense_tracker.domain.entity.ExpenseCategory;
import com.chinmay.expense_tracker.domain.entity.ExpenseEntity;
import com.chinmay.expense_tracker.dto.expense.*;
import com.chinmay.expense_tracker.exceptions.ExpenseNotFoundException;
import com.chinmay.expense_tracker.mapper.ExpenseMapper;
import com.chinmay.expense_tracker.mapper.UserMapper;
import com.chinmay.expense_tracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

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

    public List<ExpenseResponse> fetchAllExpenses() {
        return expenseRepository.findAll()
                .stream()
                .map(ExpenseMapper::toResponse)
                .toList();
    }

    public void deleteExpense(
            UUID id
    ) {
        final ExpenseEntity expenseToBeDelete = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException(id));

        expenseRepository.delete(expenseToBeDelete);
    }

    public ExpenseResponse updateExpensePatch(
            PatchExpenseRequest patchExpenseRequest,
            UUID id
    ) {
        final ExpenseEntity expenseToBeUpdate = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException(id));

        if (patchExpenseRequest.paymentDescription() != null) {
            expenseToBeUpdate.setPaymentDescription(patchExpenseRequest.paymentDescription());
        }

        if (patchExpenseRequest.amount() != null) {
            expenseToBeUpdate.setAmount(patchExpenseRequest.amount());
        }

        if (patchExpenseRequest.paymentMethod() != null) {
            expenseToBeUpdate.setPaymentMethod(patchExpenseRequest.paymentMethod());
        }

        if (patchExpenseRequest.category() != null) {
            expenseToBeUpdate.setCategory(patchExpenseRequest.category());
        }

        expenseRepository.save(expenseToBeUpdate);
        return ExpenseMapper.toResponse(expenseToBeUpdate);
    }

    public ExpenseResponse updateExpensePut(
            UpdateExpenseRequest request,
            UUID id
    ) {
        final ExpenseEntity expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException(id));

        expense.setPaymentDescription(request.paymentDescription());
        expense.setAmount(request.amount());
        expense.setPaymentMethod(request.paymentMethod());
        expense.setCategory(request.category());

        expenseRepository.save(expense);
        return ExpenseMapper.toResponse(expense);
    }


    public ExpenseSummaryResponse fetchExpensesSummary(UUID id) {
        userService.fetchUserById(id);

        final Map<ExpenseCategory, BigDecimal> categoryBreakdownMap = new HashMap<>();
        BigDecimal totalSpent = BigDecimal.ZERO;

        final List<Object[]> rows = expenseRepository.fetchExpenseSummary(id);

        for (Object[] row: rows) {
            ExpenseCategory category = (ExpenseCategory) row[0];
            BigDecimal sum = (BigDecimal) row[1];

            categoryBreakdownMap.put(category, sum);

            totalSpent = totalSpent.add(sum);

        }

        return new ExpenseSummaryResponse(totalSpent, categoryBreakdownMap);
    }

}

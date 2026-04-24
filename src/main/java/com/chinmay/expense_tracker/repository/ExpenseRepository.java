package com.chinmay.expense_tracker.repository;

import com.chinmay.expense_tracker.domain.entity.ExpenseEntity;
import com.chinmay.expense_tracker.dto.expense.ExpenseSummaryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, UUID>, JpaSpecificationExecutor<ExpenseEntity> {

    @Query("SELECT e.category, SUM(e.amount) FROM ExpenseEntity e WHERE e.user.id = :userId GROUP BY e.category")
    List<Object[]> fetchExpenseSummary(UUID userId);

}

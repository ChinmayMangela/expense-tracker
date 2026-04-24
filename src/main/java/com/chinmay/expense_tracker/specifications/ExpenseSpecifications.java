package com.chinmay.expense_tracker.specifications;

import com.chinmay.expense_tracker.domain.entity.ExpenseEntity;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class ExpenseSpecifications {

    public static Specification<ExpenseEntity> hasMinimumAmount(BigDecimal amount) {
        return (root, query, criteriaBuilder) ->
                amount == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("amount"), amount);
    }

    public static Specification<ExpenseEntity> hasDescriptionContaining(String description) {
        return (root, query, criteriaBuilder) -> {
            if (description == null || description.isEmpty()) return null;
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("paymentDescription")), "%" + description.toLowerCase() + "%");
        };
    }

    public static Specification<ExpenseEntity> hasUserId(UUID id) {
        return (root, query, criteriaBuilder) ->
                id == null ? null : criteriaBuilder.equal(root.get("user").get("id"), id);
    }

    public static Specification<ExpenseEntity> isBetweenDates(OffsetDateTime startDate, OffsetDateTime endDate) {
        return (root, query, criteriaBuilder) -> {
            if (startDate == null && endDate == null) return null;

            if (startDate == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("date"), endDate);
            }

            if (endDate == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("date"), startDate);
            }

            return criteriaBuilder.between(root.get("date"), startDate, endDate);
        };
    }
}


package com.chinmay.expense_tracker.dto.error;

import java.sql.Timestamp;
import java.util.Map;

public record ErrorResponseDto(
        int status,
        String message,
        Long timestamp,
        Map<String, String> errors
) {

}

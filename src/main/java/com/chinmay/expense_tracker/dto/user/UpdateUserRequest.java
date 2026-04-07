package com.chinmay.expense_tracker.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
        @NotBlank(message = BLANK_NAME_ERROR_MESSAGE)
        @Size(max = 40, message = NAME_SIZE_ERROR_MESSAGE)
        String name
) {

    private static final String BLANK_NAME_ERROR_MESSAGE = "Name must not be blank";
    private static final String NAME_SIZE_ERROR_MESSAGE = "Length of the name must be at most 40 characters long";
}

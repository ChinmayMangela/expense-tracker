package com.chinmay.expense_tracker.dto.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank(message = BLANK_NAME_ERROR_MESSAGE)
        @Size(max = 40, message = NAME_SIZE_ERROR_MESSAGE)
        String name,

        @NotBlank(message = BLANK_EMAIL_ERROR_MESSAGE)
        @Email(message = INVALID_EMAIL_ERROR_MESSAGE)
        String email,

        @NotBlank(message = BLANK_PASSWORD_MESSAGE)
        @Size(min = 8, max = 20, message = PASSWORD_LENGTH_ERROR_MESSAGE)
        String password
) {

    private static final String BLANK_NAME_ERROR_MESSAGE = "Name must not be blank";
    private static final String NAME_SIZE_ERROR_MESSAGE = "Length of the name must be at most 40 characters long";
    private static final String BLANK_EMAIL_ERROR_MESSAGE = "Email must not be blank";
    private static final String INVALID_EMAIL_ERROR_MESSAGE = "Please provide a valid email address";
    private static final String BLANK_PASSWORD_MESSAGE = "Password must not be blank";
    private static final String PASSWORD_LENGTH_ERROR_MESSAGE = "Password must be between 8 to 20 characters long";

}
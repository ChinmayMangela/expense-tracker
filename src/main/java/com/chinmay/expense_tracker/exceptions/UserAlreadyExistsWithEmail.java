package com.chinmay.expense_tracker.exceptions;

public class UserAlreadyExistsWithEmail extends RuntimeException {
    public UserAlreadyExistsWithEmail(String email) {
        super("User already exists with this email " + email);
    }
}

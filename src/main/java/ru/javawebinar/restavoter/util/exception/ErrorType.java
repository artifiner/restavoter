package ru.javawebinar.restavoter.util.exception;

public enum ErrorType {
    APP_ERROR("Application error"),
    ACCESS_DENIED("Access denied"),
    DATA_NOT_FOUND("Data not found"),
    DATA_ERROR("Data error"),
    VALIDATION_ERROR("Validation error");

    private final String errorMessage;

    ErrorType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
package ru.javawebinar.restavoter.util.exception;

public class DeadlinePassedException extends RuntimeException {
    public DeadlinePassedException(String message) {
        super(message);
    }
}
package ru.javawebinar.restavoter.util;

public class DeadlinePassedException extends RuntimeException {
    public DeadlinePassedException(String message) {
        super(message);
    }
}
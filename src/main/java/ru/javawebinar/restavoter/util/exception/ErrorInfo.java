package ru.javawebinar.restavoter.util.exception;

public class ErrorInfo {
    private final String url;
    private final ErrorType type;
    private final String typeMessage;
    private final String[] details;

    public ErrorInfo(CharSequence url, ErrorType type, String... details) {
        this.url = url.toString();
        this.type = type;
        this.typeMessage = type.getErrorMessage();
        this.details = details;
    }
}
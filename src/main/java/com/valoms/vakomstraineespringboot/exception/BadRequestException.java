package com.valoms.vakomstraineespringboot.exception;

public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private static final String BAD_REQUEST_EXCEPTION = "Bad request";

    public BadRequestException(String message) {
        super((message == null || message.isEmpty()) ? BAD_REQUEST_EXCEPTION : message);
    }

    public BadRequestException() {
        super(BAD_REQUEST_EXCEPTION);
    }
}

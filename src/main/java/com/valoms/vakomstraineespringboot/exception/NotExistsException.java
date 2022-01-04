package com.valoms.vakomstraineespringboot.exception;

public class NotExistsException extends RuntimeException {

    private static final String MESSAGE = "Not exists";

    public NotExistsException() {
        super(MESSAGE);
    }

    public NotExistsException(String message) {
        super(message.isEmpty() ? MESSAGE : message);
    }
}

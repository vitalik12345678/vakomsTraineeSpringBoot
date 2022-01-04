package com.valoms.vakomstraineespringboot.exception;

public class ExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String EXISTS_EXCEPTION = "Object exists";

    public ExistsException(String message) {
        super((message == null || message.isEmpty()) ? EXISTS_EXCEPTION : message);
    }

    public ExistsException() {
        super(EXISTS_EXCEPTION);
    }

}

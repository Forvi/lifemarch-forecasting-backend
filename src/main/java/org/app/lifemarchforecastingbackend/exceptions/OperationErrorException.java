package org.app.lifemarchforecastingbackend.exceptions;

public class OperationErrorException extends RuntimeException {
    public OperationErrorException(String message) {
        super(message);
    }
}

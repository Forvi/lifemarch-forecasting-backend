package org.app.lifemarchforecastingbackend.exceptions;

public class OperationError extends RuntimeException {
    public OperationError(String message) {
        super(message);
    }
}

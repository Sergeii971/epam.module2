package com.epam.esm.exception;

/**
 * The type IncorrectParameterValueException.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class IncorrectParameterValueException extends RuntimeException {
    public IncorrectParameterValueException() {
        super();
    }

    public IncorrectParameterValueException(String message) {
        super(message);
    }

    public IncorrectParameterValueException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public IncorrectParameterValueException(Throwable throwable) {
        super(throwable);
    }
}

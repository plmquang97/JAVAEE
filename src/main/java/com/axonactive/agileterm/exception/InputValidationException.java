package com.axonactive.agileterm.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class InputValidationException extends RuntimeException {
    public InputValidationException(String message) {
        super(message);
    }
}

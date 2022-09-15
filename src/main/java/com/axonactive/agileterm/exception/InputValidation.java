package com.axonactive.agileterm.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class InputValidation extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public InputValidation() {
    }

    public InputValidation(String message) {
        super(message);
    }
}

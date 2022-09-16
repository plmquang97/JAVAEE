package com.axonactive.agileterm.exception;

public class InputValidation extends RuntimeException {
    public InputValidation() {
    }

    public InputValidation(String message) {
        super(message);
    }
}

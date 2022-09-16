package com.axonactive.agileterm.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class SystemException extends RuntimeException {
    public SystemException(String message) {
        super(message);
    }
}

package com.axonactive.agileterm.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class SecurityException extends RuntimeException{
    public SecurityException (String message){
        super(message);
    }
}

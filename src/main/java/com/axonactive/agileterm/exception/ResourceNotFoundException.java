package com.axonactive.agileterm.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException( String message){
        super(message);
    };
}

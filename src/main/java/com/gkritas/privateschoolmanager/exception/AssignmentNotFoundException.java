package com.gkritas.privateschoolmanager.exception;

public class AssignmentNotFoundException extends RuntimeException{

    public AssignmentNotFoundException(String message) {
        super(message);
    }

    public AssignmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

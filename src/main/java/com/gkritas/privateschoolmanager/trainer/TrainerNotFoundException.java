package com.gkritas.privateschoolmanager.trainer;

public class TrainerNotFoundException extends RuntimeException{

    public TrainerNotFoundException(String message) {
        super(message);
    }

    public TrainerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

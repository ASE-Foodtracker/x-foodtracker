package de.jmf.application.exceptions;

public class notExistentException extends RuntimeException {
    public notExistentException(String message) {
        super(message);
    }
}

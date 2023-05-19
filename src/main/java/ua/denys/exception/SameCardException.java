package ua.denys.exception;

public class SameCardException extends RuntimeException{
    public SameCardException(String message) {
        super(message);
    }
}

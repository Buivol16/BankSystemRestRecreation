package ua.denys.exception;

public class CardIsNotExistsException extends RuntimeException{
    public CardIsNotExistsException(String message) {
        super(message);
    }
}

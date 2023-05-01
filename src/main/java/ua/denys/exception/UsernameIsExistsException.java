package ua.denys.exception;

public class UsernameIsExistsException extends RuntimeException{
    public UsernameIsExistsException(String message) {
        super(message);
    }
}

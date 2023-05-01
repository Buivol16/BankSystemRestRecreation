package ua.denys.exception;

public class InvalidUsernameOrPasswordException extends RuntimeException{
    public InvalidUsernameOrPasswordException(String msg) {
        super(msg);
    }
}

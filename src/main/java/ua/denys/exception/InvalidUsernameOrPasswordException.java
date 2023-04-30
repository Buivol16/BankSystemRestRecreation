package ua.denys.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidUsernameOrPasswordException extends AuthenticationException {
    public InvalidUsernameOrPasswordException(String msg) {
        super(msg);
    }
}

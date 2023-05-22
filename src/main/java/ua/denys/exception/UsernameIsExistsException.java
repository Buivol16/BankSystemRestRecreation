package ua.denys.exception;

import jakarta.servlet.http.HttpServletResponse;

public class UsernameIsExistsException extends BankException{
    public UsernameIsExistsException(String message, HttpServletResponse response, String redirectLink) {
        super(message, null, response, redirectLink);
    }
}

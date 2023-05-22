package ua.denys.exception;

import jakarta.servlet.http.HttpServletResponse;

public class NameExistsException extends BankException{
    public NameExistsException(String message, String username, HttpServletResponse response, String redirectLink) {
        super(message, username, response, redirectLink);
    }
}

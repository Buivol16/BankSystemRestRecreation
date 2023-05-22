package ua.denys.exception;

import jakarta.servlet.http.HttpServletResponse;

public class CardIsNotExistsException extends BankException{
    public CardIsNotExistsException(String message, String username, HttpServletResponse response, String redirectLink) {
        super(message, username, response, redirectLink);
    }
}

package ua.denys.exception;

import jakarta.servlet.http.HttpServletResponse;

public class NotEnoughMoneyException extends BankException {
    public NotEnoughMoneyException(String message, String username, HttpServletResponse response, String redirectLink) {
        super(message, username, response, redirectLink);
    }
}

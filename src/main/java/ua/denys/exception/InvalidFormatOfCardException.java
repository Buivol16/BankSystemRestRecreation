package ua.denys.exception;

import jakarta.servlet.http.HttpServletResponse;

public class InvalidFormatOfCardException extends BankException{

    public InvalidFormatOfCardException(String message, String username) {
        super(message, username, null, null);
    }
}

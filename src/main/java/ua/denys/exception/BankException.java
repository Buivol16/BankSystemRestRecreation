package ua.denys.exception;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BankException extends RuntimeException{
    private String username;

    @SneakyThrows
    public BankException(String message, String username, HttpServletResponse response, String redirectLink) {
        super(message);
        this.username = username;
        if (response != null && redirectLink != null) response.sendRedirect(redirectLink);
    }
}

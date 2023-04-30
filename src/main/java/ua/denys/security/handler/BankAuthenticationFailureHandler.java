package ua.denys.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import ua.denys.exception.InvalidUsernameOrPasswordException;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

public class BankAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        final var errorMessage = "Incorrect user's data, please check fields";
        response.sendError(SC_BAD_REQUEST, errorMessage);
        throw new InvalidUsernameOrPasswordException(errorMessage);
    }
}

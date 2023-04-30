package ua.denys.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import ua.denys.exception.InvalidUsernameOrPasswordException;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static ua.denys.consts.ErrorMessage.INCORRECT_DATA_EXCEPTION_MESSAGE;

public class BankAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.sendError(SC_BAD_REQUEST, INCORRECT_DATA_EXCEPTION_MESSAGE);
        throw new InvalidUsernameOrPasswordException(INCORRECT_DATA_EXCEPTION_MESSAGE);
    }
}

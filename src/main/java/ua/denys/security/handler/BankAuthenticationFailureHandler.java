package ua.denys.security.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import ua.denys.controller.LoginController;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_GATEWAY;
import static ua.denys.consts.ErrorMessage.INCORRECT_DATA_EXCEPTION_MESSAGE;

@RequiredArgsConstructor
public class BankAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final LoginController loginController;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.sendError(SC_BAD_GATEWAY, INCORRECT_DATA_EXCEPTION_MESSAGE);
        loginController.setExceptionMessage(INCORRECT_DATA_EXCEPTION_MESSAGE);
    }
}

package ua.denys.security.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class BankAuthenticationManager implements AuthenticationManager {

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final var password = passwordEncoder.encode(authentication.getCredentials().toString());
        final var usernamePasswordToken = new UsernamePasswordAuthenticationToken(
                authentication.getPrincipal(),
                password,
                authentication.getAuthorities());
        return usernamePasswordToken;
    }
}

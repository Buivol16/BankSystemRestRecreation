package ua.denys.security.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.math.BigInteger;
import java.security.MessageDigest;

@RequiredArgsConstructor
@Data
public class LoginPasswordTokenService {
    private final String username;
    private final String password;

    @SneakyThrows
    public String generateToken() {
        final var messageDigest = MessageDigest.getInstance("SHA-512");
        final var encryptedToken = messageDigest.digest((username+password).getBytes());
        final var bigInt = new BigInteger(encryptedToken);
        final var hashStr = bigInt.toString(16);
        return hashStr;
    }
}

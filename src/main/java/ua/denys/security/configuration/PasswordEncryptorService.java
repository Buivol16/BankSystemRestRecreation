package ua.denys.security.configuration;

import lombok.SneakyThrows;

import java.math.BigInteger;
import java.security.MessageDigest;

public class PasswordEncryptorService{

    @SneakyThrows
    public static String generateHashPassword(String password) {
        final var messageDigest = MessageDigest.getInstance("SHA-512");
        final var encryptedToken = messageDigest.digest(password.getBytes());
        final var bigInt = new BigInteger(encryptedToken);
        final var hashStr = bigInt.toString(16);
        return hashStr;
    }

}

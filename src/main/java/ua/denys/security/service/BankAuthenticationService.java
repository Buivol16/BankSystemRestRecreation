package ua.denys.security.service;

import org.springframework.security.core.context.SecurityContextHolder;

public class BankAuthenticationService {

    public static String getUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}

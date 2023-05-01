package ua.denys.security.service;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationManager {
    public static String getUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}

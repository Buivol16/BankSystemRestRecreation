package ua.denys.controller;

import org.hibernate.procedure.NoSuchParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.denys.security.service.LoginPasswordTokenService;

@RestController
public class TokenController {

    @GetMapping("/gettoken")
    public String getToken(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        if(!username.isEmpty() || !password.isEmpty()) return new LoginPasswordTokenService(username, password).generateToken();
        else throw new NoSuchParameterException("Required filled params, please, check these ones.");
    }
}

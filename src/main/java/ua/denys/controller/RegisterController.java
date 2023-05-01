package ua.denys.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.denys.model.user.UserSignUpProjection;

import java.io.IOException;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @GetMapping
    public String registerPage(){
        return "registerPage";
    }

    @PostMapping
    public void registerUser(@RequestBody UserSignUpProjection userSignUpProjection) throws IOException {

    }

}

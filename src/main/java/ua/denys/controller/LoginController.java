package ua.denys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.denys.exception.InvalidUsernameOrPasswordException;

@Controller
@RequestMapping(path = "/login")
public class LoginController {
    @GetMapping
    @ExceptionHandler(InvalidUsernameOrPasswordException.class)
    public String loginPage(Model model, InvalidUsernameOrPasswordException exception){
        model.addAttribute("errorMessage", exception.getMessage());
        return "loginpage";
    }
}

package ua.denys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.denys.exception.InvalidUsernameOrPasswordException;

import static ua.denys.consts.ErrorMessage.INCORRECT_DATA_EXCEPTION_MESSAGE;

@Controller
@RequestMapping(path = "/login")
public class LoginController {
    private String errorMessage = "";

    @GetMapping
    public String loginPage(Model model){
        model.addAttribute("errorMessage", errorMessage);
        return "loginpage";
    }

    @ExceptionHandler(InvalidUsernameOrPasswordException.class)
    public void errorMessage(InvalidUsernameOrPasswordException e){
        errorMessage = INCORRECT_DATA_EXCEPTION_MESSAGE;
    }
}

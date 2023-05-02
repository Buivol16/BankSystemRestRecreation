package ua.denys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static ua.denys.consts.ErrorMessage.INCORRECT_DATA_EXCEPTION_MESSAGE;

@Controller
@RequestMapping(path = "/login")
public class LoginController {

    @GetMapping
    public String loginPage(Model model, @RequestParam(required = false) String error){
        if (error != null && error.equals("true"))
            model.addAttribute("errorMessage", INCORRECT_DATA_EXCEPTION_MESSAGE);

        return "loginpage";
    }
}

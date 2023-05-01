package ua.denys.controller;

import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/login")
public class LoginController {

    @Setter
    private String exceptionMessage = "";

    @GetMapping
    public String loginPage(Model model){
        model.addAttribute("errorMessage", exceptionMessage);
        return "loginpage";
    }
}

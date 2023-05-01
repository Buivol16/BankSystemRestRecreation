package ua.denys.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.denys.facade.UserFacade;

@Controller
@RequestMapping(value = "/home")
@RequiredArgsConstructor
public class HomeController {

    private final UserFacade userFacade;

    @GetMapping
    public String home(Model model){
        model.addAttribute("title", "Welcome, " + userFacade.getFirstName());
        return "bankindexpage";
    }
}

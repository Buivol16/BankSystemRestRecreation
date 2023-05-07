package ua.denys.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.denys.facade.CardFacade;
import ua.denys.facade.UserFacade;
import ua.denys.model.user.UserSignUpProjection;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final UserFacade userFacade;
    private final CardFacade cardFacade;

    @GetMapping
    public String registerPage(Model model){
        final var signupUser = new UserSignUpProjection();
        model.addAttribute("userSignup", signupUser);
        return "registerPage";
    }

    @SneakyThrows
    @PostMapping
    public String registerUser(UserSignUpProjection userSignUpProjection) {
        final var user = userFacade.registerUser(userSignUpProjection);
        cardFacade.registerCard(user);
        return "redirect://home";
    }

}

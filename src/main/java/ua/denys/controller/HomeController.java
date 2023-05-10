package ua.denys.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.denys.facade.CardFacade;
import ua.denys.facade.UserFacade;
import ua.denys.security.service.BankAuthenticationService;

import java.io.File;

@Controller
@RequestMapping(value = "/home")
@RequiredArgsConstructor
public class HomeController {

    private final UserFacade userFacade;
    private final CardFacade cardFacade;

    @GetMapping
    public String home(Model model){
        final var icon = new File("src/main/resources/static/banklogo.ico");
        final var user = userFacade.findUserByUsernameOrThrowException(BankAuthenticationService.getUsername());
        final var card = cardFacade.findCardByConsumerIdOrThrowException(user);
        model.addAttribute("title", "Welcome, " + userFacade.getFirstName());
        model.addAttribute("cardNum", card.getCardNumber());
        model.addAttribute("cardCvvNum", card.getCvvCode());
        model.addAttribute("cardMoneyCount", card.getMoneyCount()+" UAH");
        model.addAttribute("iconFile", icon);
        return "bankindexpage";
    }
}

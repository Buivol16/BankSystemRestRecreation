package ua.denys.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.denys.exception.CardIsNotExistsException;
import ua.denys.exception.InvalidFormatOfCardException;
import ua.denys.exception.NotEnoughMoneyException;
import ua.denys.exception.SameCardException;
import ua.denys.facade.CardFacade;
import ua.denys.facade.TransferMoneyFacade;
import ua.denys.model.transfermoney.TransferMoneyDTO;

@Controller
@RequestMapping("/transfermoney")
@RequiredArgsConstructor
public class TransferController {

    private final CardFacade cardFacade;
    private final TransferMoneyFacade transferMoneyFacade;

    private String errorMessage = "";

    @GetMapping
    public String transferMoney(Model model){
        model.addAttribute("cards", cardFacade.getCardsOfUser());
        model.addAttribute("transfer", TransferMoneyDTO.builder().build());
        model.addAttribute("errorMessage", errorMessage);
        errorMessage = "";
        return "transfermoneypage";
    }

    @SneakyThrows
    @PostMapping
    public String doTransferMoney(TransferMoneyDTO dto, HttpServletResponse response){
        transferMoneyFacade.doTransfer(dto, response);
        return "redirect:/home";
    }

    @ExceptionHandler(value = InvalidFormatOfCardException.class)
    private void handleFormatOfCard(InvalidFormatOfCardException exception){
        errorMessage = exception.getMessage();
    }

    @ExceptionHandler(value = CardIsNotExistsException.class)
    private void handleExistingOfCard(CardIsNotExistsException exception){
        errorMessage = exception.getMessage();
    }

    @ExceptionHandler(value = NotEnoughMoneyException.class)
    private void handleEnoughOfMoney(NotEnoughMoneyException exception){
        errorMessage = exception.getMessage();
    }

    @ExceptionHandler(value = SameCardException.class)
    private void handleSameOfCard(SameCardException exception){
        errorMessage = exception.getMessage();
    }
}

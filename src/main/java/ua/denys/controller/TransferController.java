package ua.denys.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.denys.facade.CardFacade;
import ua.denys.facade.TransferMoneyFacade;
import ua.denys.model.transfermoney.TransferMoneyDTO;

@Controller
@RequestMapping("/transfermoney")
@RequiredArgsConstructor
public class TransferController {

    private final CardFacade cardFacade;
    private final TransferMoneyFacade transferMoneyFacade;

    @GetMapping
    public String transferMoney(Model model){
        model.addAttribute("cards", cardFacade.getCardsOfUser());
        model.addAttribute("transfer", TransferMoneyDTO.builder().build());
        return "transfermoneypage";
    }

    @PostMapping
    public String doTransferMoney(TransferMoneyDTO dto){
        transferMoneyFacade.doTransfer(dto);
        return "redirect:/home";
    }
}

package ua.denys.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.denys.exception.BankException;
import ua.denys.exception.NotEnoughMoneyException;
import ua.denys.exception.NameExistsException;
import ua.denys.facade.DebtorToPayFacade;
import ua.denys.security.service.BankAuthenticationService;

@Controller
@RequestMapping("/payservices")
@RequiredArgsConstructor
public class PayServiceController {
    private final DebtorToPayFacade debtorToPayFacade;

    private BankException exception = null;

    @GetMapping
    public String payservicePage(Model model) {
        model.addAttribute("debtortopays", debtorToPayFacade.getDebtorToPaysByUsername(BankAuthenticationService.getUsername()));
        if (exception != null && BankAuthenticationService.getUsername().equals(exception.getUsername()))
            model.addAttribute("errorMessage", exception.getMessage());
        return "payservicepage";
    }

    @PostMapping(path = "/{id}")
    public String payForService(@PathVariable("id") Long id, HttpServletResponse response) {
        debtorToPayFacade.payForService(id, response);
        return "redirect:/payservices";
    }

    @ExceptionHandler(value = {NotEnoughMoneyException.class})
    public void handleEnoughOfMoneyException(NotEnoughMoneyException exception) {
        this.exception = exception;
    }

    @ExceptionHandler(value = NameExistsException.class)
    public void handleNameExistingException(NameExistsException exception){
        this.exception = exception;
    }
}

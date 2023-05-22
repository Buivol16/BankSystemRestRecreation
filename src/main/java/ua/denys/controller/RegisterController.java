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
import ua.denys.exception.UsernameIsExistsException;
import ua.denys.facade.CardFacade;
import ua.denys.facade.DebtorToPayFacade;
import ua.denys.facade.UserFacade;
import ua.denys.model.user.UserSignUpProjection;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final UserFacade userFacade;
    private final CardFacade cardFacade;
    private final DebtorToPayFacade debtorToPayFacade;

    private String exceptionMessage = "";
    @GetMapping
    public String registerPage(Model model){
        final var signupUser = new UserSignUpProjection();
        model.addAttribute("userSignup", signupUser);
        model.addAttribute("errorMessage", exceptionMessage);
        exceptionMessage = "";
        return "registerPage";
    }

    @SneakyThrows
    @PostMapping
    public String registerUser(UserSignUpProjection userSignUpProjection, HttpServletResponse response) {
        final var user = userFacade.registerUser(userSignUpProjection, response);
        debtorToPayFacade.registerNewDebtor(user);
        cardFacade.registerCard(user);
        return "redirect:/login";
    }

    @ExceptionHandler(value = UsernameIsExistsException.class)
    public void handleUsernameException(UsernameIsExistsException usernameIsExistsException){
        this.exceptionMessage = usernameIsExistsException.getMessage();
    }
}

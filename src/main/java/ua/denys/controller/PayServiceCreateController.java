package ua.denys.controller;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.denys.facade.DebtorToPayFacade;
import ua.denys.facade.RentServiceFacade;
import ua.denys.facade.UserFacade;
import ua.denys.model.service.RentServiceDTO;
import ua.denys.security.service.BankAuthenticationService;
import ua.denys.utils.mapper.RentServiceMapper;

@Controller
@RequestMapping("/createservice")
@RequiredArgsConstructor
public class PayServiceCreateController {
    private final RentServiceFacade rentServiceFacade;
    private final DebtorToPayFacade debtorToPayFacade;
    private final UserFacade userFacade;

    @GetMapping
    public String serviceCreationPage(Model model){
        model.addAttribute("serviceCreationDTO", new RentServiceDTO());
        return "rentservicecreationpage";
    }

    @PostMapping
    public String createService(RentServiceDTO rentServiceDTO){
        final var service = Mappers.getMapper(RentServiceMapper.class).DTOtoRentService(rentServiceDTO);
        final var founder = userFacade.findUserByUsernameOrThrowException(BankAuthenticationService.getUsername());
        service.setFounderId(founder);
        final var rentService = rentServiceFacade.saveService(service);
        debtorToPayFacade.createDebtorsForPay(rentService);
        return "redirect:/payservices";
    }

}

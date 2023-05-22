package ua.denys.facade;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.denys.exception.NotEnoughMoneyException;
import ua.denys.model.service.DebtorToPay;
import ua.denys.model.service.RentService;
import ua.denys.model.user.User;
import ua.denys.repository.DebtorToPayRepository;
import ua.denys.security.service.BankAuthenticationService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DebtorToPayFacade {
    private final DebtorToPayRepository debtorToPayRepository;
    private final RentServiceFacade rentServiceFacade;
    private final UserFacade userFacade;
    private final CardFacade cardFacade;

    @Transactional
    public boolean payForService(Long id, HttpServletResponse response){
        final var debtorToPay = debtorToPayRepository.findById(id).get();
        final var payer = userFacade.findUserByUsernameOrThrowException(BankAuthenticationService.getUsername());
        final var cardOfPayer = cardFacade.findCardByConsumerIdOrThrowException(payer);
        final var founder = debtorToPay.getRentServiceToPaid().getFounderId();
        final var founderCard = cardFacade.findCardByConsumerIdOrThrowException(founder);
        if (cardOfPayer.getMoneyCount() < debtorToPay.getMoneyToPaid()) throw new NotEnoughMoneyException("You have no money to paid for this service.", payer.getUsername(), response, "/payservices");
        cardOfPayer.setMoneyCount(cardOfPayer.getMoneyCount()-debtorToPay.getMoneyToPaid());
        founderCard.setMoneyCount(founderCard.getMoneyCount()+debtorToPay.getMoneyToPaid());
        cardFacade.saveCard(cardOfPayer);
        cardFacade.saveCard(founderCard);
        debtorToPay.setMoneyToPaid(0d);
        debtorToPayRepository.save(debtorToPay);
        return true;
    }

    public List<DebtorToPay> getDebtorToPaysByUsername(String username){
        final var user = userFacade.findUserByUsernameOrThrowException(username);
        final var debtorToPays = debtorToPayRepository.findAllByDebtor(user);
        return debtorToPays;
    }

    public Set<DebtorToPay> createDebtorsForPay(RentService rentService){
        final var founder = userFacade.findUserByUsernameOrThrowException(BankAuthenticationService.getUsername());
        final var debtorToPay = new DebtorToPay();
        final var users = userFacade.findAll();
        final var debtorsCollection = new HashSet<DebtorToPay>();
        users.remove(users.indexOf(founder));
        users.forEach(user -> {
            debtorToPay.setMoneyToPaid(rentService.getRentCost());
            debtorToPay.setRentServiceToPaid(rentService);
            debtorToPay.setDebtor(user);
            debtorsCollection.add(debtorToPayRepository.save(debtorToPay));
        });
        return debtorsCollection;
    }

    public Set<DebtorToPay> registerNewDebtor(User debtor){
        final var rents = rentServiceFacade.findAll();
        if (rents.isEmpty()) return null;
        final var debtors = new HashSet<DebtorToPay>();
        rents.forEach(rentService -> {
            final var debtorToPay = new DebtorToPay();
            debtorToPay.setDebtor(debtor);
            debtorToPay.setRentServiceToPaid(rentService);
            debtorToPay.setMoneyToPaid(rentService.getRentCost());
            debtorToPayRepository.save(debtorToPay);
        });
        return debtors;
    }
}

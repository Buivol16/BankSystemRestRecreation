package ua.denys.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.denys.repository.DebtorToPayRepository;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class PayableServiceScheduler {
    private final DebtorToPayRepository debtorToPayRepository;
    @Scheduled(timeUnit = TimeUnit.MINUTES, fixedRate = 1l)
    public void check(){
        addDebt();
    }

    private void addDebt(){
        final var debtorToPays = debtorToPayRepository.findAll();
        if (debtorToPays.isEmpty()) return;
        debtorToPays.forEach(debtorToPay -> debtorToPay.setMoneyToPaid(debtorToPay.getMoneyToPaid()*2));
        debtorToPayRepository.saveAll(debtorToPays);
    }
}

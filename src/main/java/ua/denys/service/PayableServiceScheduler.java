package ua.denys.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.denys.repository.DebtorToPayRepository;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class PayableServiceScheduler {
    private final DebtorToPayRepository debtorToPayRepository;
    //todo make cron expression about every minute
    @Scheduled(cron = "")
    public void check(){
        addDebt();
    }

    private void addDebt(){
        final var debtorToPays = debtorToPayRepository.findAll();
        if (debtorToPays.isEmpty()) return;
        debtorToPays.forEach(debtorToPay -> {
            final var random = new Random().nextDouble(0.01, 3);
            debtorToPay.setMoneyToPaid(debtorToPay.getMoneyToPaid()+random);
        });
        debtorToPayRepository.saveAll(debtorToPays);
    }
}

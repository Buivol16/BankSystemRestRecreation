package ua.denys.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.denys.repository.RentServiceRepository;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class PayableServiceScheduler {
    private final RentServiceRepository rentServiceRepository;

    @Scheduled(cron = "0 0/1 0 ? * * *")
    public void check(){
        addDebt();
    }

    private void addDebt(){
        final var rents = rentServiceRepository.findAll();
        rents.forEach(rentService -> {
            final var random = new Random().nextDouble(0.1, 1);
            final var moneyToPay = rentService.getMoneyCountToPay()+random;
            rentService.setMoneyCountToPay(moneyToPay);
        });
        rentServiceRepository.saveAll(rents);
    }
}

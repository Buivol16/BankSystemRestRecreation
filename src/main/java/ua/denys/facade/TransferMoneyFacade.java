package ua.denys.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TransferMoneyFacade {

    private final CardFacade cardFacade;

    @Transactional
    public boolean doTransfer(){
        return false;
    }
}

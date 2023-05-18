package ua.denys.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.denys.exception.NotEnoughMoneyException;
import ua.denys.model.transfermoney.TransferMoneyDTO;

import static ua.denys.utils.consts.ErrorMessage.NOT_ENOUGH_MONEY_EXCEPTION_MESSAGE;

@Component
@RequiredArgsConstructor
public class TransferMoneyFacade {

    private final CardFacade cardFacade;

    @Transactional
    public boolean doTransfer(TransferMoneyDTO transferMoneyDTO){
        final var cardFrom = cardFacade.findCardByCardNumberOrThrowException(transferMoneyDTO.getConsumerCardNumber());
        final var cardTo = cardFacade.findCardByCardNumberOrThrowException(transferMoneyDTO.getTransferCardNumber());
        final var valueToTransfer = transferMoneyDTO.getMoneyCountToTransfer();
        if (cardFrom.getMoneyCount() < valueToTransfer) throw new NotEnoughMoneyException(NOT_ENOUGH_MONEY_EXCEPTION_MESSAGE);
        final var cardFromMoneyCount = cardFrom.getMoneyCount();
        final var cardToMoneyCount = cardTo.getMoneyCount();
        cardFrom.setMoneyCount(cardFromMoneyCount-valueToTransfer);
        cardTo.setMoneyCount(cardToMoneyCount+valueToTransfer);
        cardFacade.saveCard(cardFrom);
        cardFacade.saveCard(cardTo);
        return true;
    }
}

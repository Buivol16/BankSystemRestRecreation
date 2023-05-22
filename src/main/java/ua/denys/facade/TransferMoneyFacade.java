package ua.denys.facade;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.denys.exception.CardIsNotExistsException;
import ua.denys.exception.NotEnoughMoneyException;
import ua.denys.exception.SameCardException;
import ua.denys.model.transfermoney.TransferMoneyDTO;
import ua.denys.security.service.BankAuthenticationService;

import java.util.regex.Pattern;

import static ua.denys.utils.consts.CardConst.CARD_NUMBER_REGEX;
import static ua.denys.utils.consts.ErrorMessage.NOT_ENOUGH_MONEY_EXCEPTION_MESSAGE;

@Component
@RequiredArgsConstructor
public class TransferMoneyFacade {

    private final CardFacade cardFacade;

    @Transactional
    public boolean doTransfer(TransferMoneyDTO transferMoneyDTO, HttpServletResponse response){
        final var username = BankAuthenticationService.getUsername();
        final var redirectLink = "/bank/transfermoney";
        if (!isValid(transferMoneyDTO.getTransferCardNumber())) throw new CardIsNotExistsException("Card with this number is not exists.", username, response, redirectLink);
        final var cardFrom = cardFacade.findCardByCardNumberOrThrowException(transferMoneyDTO.getConsumerCardNumber());
        final var cardTo = cardFacade.findCardByCardNumberOrThrowException(transferMoneyDTO.getTransferCardNumber());
        final var valueToTransfer = transferMoneyDTO.getMoneyCountToTransfer();
        final var cardFromMoneyCount = cardFrom.getMoneyCount();
        final var cardToMoneyCount = cardTo.getMoneyCount();
        if (isSameCard(cardFrom.getCardNumber(), cardTo.getCardNumber())) throw new SameCardException("You can't send money to the same card.", username, response, redirectLink);
        if (cardFromMoneyCount < valueToTransfer) throw new NotEnoughMoneyException(NOT_ENOUGH_MONEY_EXCEPTION_MESSAGE, username, response, redirectLink);
        cardFrom.setMoneyCount(cardFromMoneyCount-valueToTransfer);
        cardTo.setMoneyCount(cardToMoneyCount+valueToTransfer);
        cardFacade.saveCard(cardFrom);
        cardFacade.saveCard(cardTo);
        return true;
    }

    private boolean isValid(String cardNum){
        return Pattern.matches(CARD_NUMBER_REGEX, cardNum);
    }

    private boolean isSameCard(String cardNum, String secondCardNum){
        return cardNum.equals(secondCardNum);
    }

}

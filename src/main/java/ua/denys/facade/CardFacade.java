package ua.denys.facade;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ua.denys.exception.CardIsNotExistsException;
import ua.denys.exception.CardIsNotExistsForUserException;
import ua.denys.exception.InvalidFormatOfCardException;
import ua.denys.model.card.Card;
import ua.denys.model.card.CardDTO;
import ua.denys.model.user.User;
import ua.denys.repository.CardRepository;
import ua.denys.security.service.BankAuthenticationService;
import ua.denys.utils.mapper.CardMapper;

import java.util.List;
import java.util.Random;

import static ua.denys.utils.consts.CardConst.CARD_NUMBER_FORMAT;
import static ua.denys.utils.consts.ErrorMessage.CARD_IS_NOT_EXISTS_EXCEPTION_MESSAGE;

@Component
@RequiredArgsConstructor
public class CardFacade {
    private final CardRepository cardRepository;
    private final UserFacade userFacade;

    public Card findCardByConsumerIdOrThrowException(User user) {
        final var cardOptional = cardRepository.findByConsumerId(user);
        if (cardOptional.isEmpty()) throw new CardIsNotExistsForUserException(user.getUsername());
        return cardOptional.get();
    }

    public Card findCardByCardNumberOrThrowException(String cardNum){
        final var username = BankAuthenticationService.getUsername();
        if (cardNum.length() != 24) throw new InvalidFormatOfCardException("Invalid format of card.", username);
        final var cardOptional = cardRepository.findByCardNumber(cardNum);
        if (cardOptional.isEmpty()) throw new CardIsNotExistsException(CARD_IS_NOT_EXISTS_EXCEPTION_MESSAGE, username, null, null);
        return cardOptional.get();
    }

    public Card registerCard(User consumer) {
        final var cardMapper = Mappers.getMapper(CardMapper.class);
        final var cardNum =
                String.format(CARD_NUMBER_FORMAT,
                        getNextNumber(),
                        getNextNumber(),
                        getNextNumber(),
                        getNextNumber(),
                        getNextNumber());
        final var cardDTO = CardDTO.builder()
                .cardNumber(cardNum)
                .moneyCount(1000d)
                .cvvCode(getNextNumber())
                .consumerId(consumer)
                .build();
        final var card = cardMapper.cardDTOToCard(cardDTO);
        return saveCard(card);
    }

    public Card saveCard(Card card){
        return cardRepository.save(card);
    }

    private String getNextNumber() {
        final var random = new Random();
        return String.valueOf(random.nextInt(1000, 9999));
    }

    public List<Card> getCardsOfUser() {
        final var username = BankAuthenticationService.getUsername();
        final var user = userFacade.findUserByUsernameOrThrowException(username);
        return cardRepository.findAllByConsumerId(user);
    }
}

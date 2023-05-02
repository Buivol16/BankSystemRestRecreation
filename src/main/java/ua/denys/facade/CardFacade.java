package ua.denys.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.denys.model.card.Card;
import ua.denys.model.user.User;
import ua.denys.repository.CardRepository;

@Component
@RequiredArgsConstructor
public class CardFacade {

    private final CardRepository cardRepository;

    public Card findCardByConsumerIdOrThrowException(User user){
        final var cardOptional = cardRepository.findByConsumerId(user);
        if (cardOptional.isEmpty()) throw new
    }
}

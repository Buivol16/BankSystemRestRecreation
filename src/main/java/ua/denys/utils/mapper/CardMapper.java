package ua.denys.utils.mapper;

import org.mapstruct.Mapper;
import ua.denys.model.card.Card;
import ua.denys.model.card.CardDTO;

@Mapper
public interface CardMapper {
    Card cardDTOToCard(CardDTO cardDTO);
}

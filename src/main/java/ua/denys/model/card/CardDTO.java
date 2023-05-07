package ua.denys.model.card;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ua.denys.model.user.User;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardDTO {
    String cardNumber;
    String cvvCode;
    Double moneyCount;
    User consumerId;
}

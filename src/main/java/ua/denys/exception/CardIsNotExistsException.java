package ua.denys.exception;

import static ua.denys.utils.consts.ErrorMessage.CARD_IS_NOT_EXISTS_EXCEPTION_MESSAGE;

public class CardIsNotExistsException extends RuntimeException{
    public CardIsNotExistsException(String username) {
        super(String.format(CARD_IS_NOT_EXISTS_EXCEPTION_MESSAGE, username));
    }
}

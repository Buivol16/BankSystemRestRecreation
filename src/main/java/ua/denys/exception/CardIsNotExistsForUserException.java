package ua.denys.exception;

import static ua.denys.utils.consts.ErrorMessage.CARD_IS_NOT_EXISTS_FOR_USER_EXCEPTION_MESSAGE;

public class CardIsNotExistsForUserException extends BankException{

    public CardIsNotExistsForUserException(String username) {
        super(String.format(CARD_IS_NOT_EXISTS_FOR_USER_EXCEPTION_MESSAGE, username), username, null, null);
    }
}

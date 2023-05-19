package ua.denys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.denys.model.card.Card;
import ua.denys.model.user.User;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByConsumerId(User consumer);
    List<Card> findAllByConsumerId(User consumer);
    Optional<Card> findByCardNumber(String cardNum);
}

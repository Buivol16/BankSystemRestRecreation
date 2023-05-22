package ua.denys.model.card;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.denys.model.user.User;

@Entity
@Table(name = "card")
@NoArgsConstructor
@Data
public class Card {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "cvv_code")
    private String cvvCode;
    @Column(name = "money_count")
    private Double moneyCount;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id", nullable = false)
    private User consumerId;
}

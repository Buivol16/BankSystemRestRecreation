package ua.denys.model.card;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.denys.model.user.User;

@Entity
@Table(name = "card")
@NoArgsConstructor
@Data
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "cvv_code")
    private String cvvCode;
    @Column(name = "money_count")
    private Double moneyCount;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "consumer_id")
    private User consumerId;
}

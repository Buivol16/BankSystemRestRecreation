package ua.denys.model.service;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.denys.model.user.User;

import java.util.List;

@Entity
@Table(name = "debtor_to_pay")
@Data
@NoArgsConstructor
public class DebtorToPay {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "debtor_ids")
    private User debtor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rent_service_to_paid")
    private RentService rentServiceToPaid;

    @Column(name = "money_to_paid")
    private Double moneyToPaid;
}
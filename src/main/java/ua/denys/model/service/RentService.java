package ua.denys.model.service;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.denys.model.user.User;

import java.util.List;

@Entity
@Table(name = "rent_service")
@Data
@NoArgsConstructor
public class RentService {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "service_name")
    private String serviceName;
    @Column(name = "money_count_to_pay")
    private Double moneyCountToPay;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "debtor_id")
    private List<User> debtorId;
}

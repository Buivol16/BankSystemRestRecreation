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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "founder_id")
    private User founderId;

    @Column(name = "rent_cost")
    private Double rentCost;
}

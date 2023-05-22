package ua.denys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.denys.model.service.DebtorToPay;
import ua.denys.model.user.User;

import java.util.List;

public interface DebtorToPayRepository extends JpaRepository<DebtorToPay, Long> {
    List<DebtorToPay> findAllByDebtor(User debtor);
}

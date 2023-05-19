package ua.denys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.denys.model.service.RentService;
import ua.denys.model.user.User;

import java.util.List;

public interface RentServiceRepository extends JpaRepository<RentService, Long> {
    List<RentService> findAllByDebtorId(User debtorId);
}

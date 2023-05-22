package ua.denys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.denys.model.service.RentService;

public interface RentServiceRepository extends JpaRepository<RentService, Long> {
}

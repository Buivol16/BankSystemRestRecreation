package ua.denys.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.denys.model.service.RentService;
import ua.denys.repository.RentServiceRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RentServiceFacade {
    private final RentServiceRepository rentServiceRepository;

    public RentService saveService(RentService rentService){
        return rentServiceRepository.save(rentService);
    }

    public List<RentService> findAll(){
        return rentServiceRepository.findAll();
    }

}

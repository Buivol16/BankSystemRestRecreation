package ua.denys.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.denys.model.service.RentService;
import ua.denys.model.service.RentServiceDTO;

@Mapper
public interface RentServiceMapper {
    @Mapping(source = "rentCost", target = "rentCost")
    RentService DTOtoRentService(RentServiceDTO rentServiceDTO);
}

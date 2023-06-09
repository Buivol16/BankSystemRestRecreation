package ua.denys.model.service;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class RentServiceDTO {
    String serviceName;
    Double rentCost;
}

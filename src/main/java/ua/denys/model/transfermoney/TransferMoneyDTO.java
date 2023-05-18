package ua.denys.model.transfermoney;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferMoneyDTO {
    String consumerCardNumber;
    String transferCardNumber;
    Double moneyCountToTransfer;
}

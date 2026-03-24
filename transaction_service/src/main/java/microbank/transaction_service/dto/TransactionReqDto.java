package microbank.transaction_service.dto;


import lombok.Builder;
import lombok.Data;
import microbank.transaction_service.enums.TransactionType;

import java.math.BigDecimal;

@Builder
@Data
public class TransactionReqDto {
    private String fromAccountId;

    private String toAccountId;

    private BigDecimal amount;

//    private String currency;

    private TransactionType transactionType;

    private String description;

    private String idempotencyKey;
}

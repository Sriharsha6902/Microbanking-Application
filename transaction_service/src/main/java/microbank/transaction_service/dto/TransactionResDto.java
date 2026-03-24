package microbank.transaction_service.dto;

import lombok.Builder;
import lombok.Data;
import microbank.transaction_service.enums.TransactionStatus;
import microbank.transaction_service.enums.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Data
public class TransactionResDto {
    private String transactionReference;

    private TransactionStatus status;

    private BigDecimal amount;

    private TransactionType transactionType;

    private String fromAccountId;

    private String toAccountId;

    private Instant createdAt;

    private Instant processedAt;

    private String failureReason;
}
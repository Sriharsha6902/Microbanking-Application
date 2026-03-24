package microbank.transaction_service.util;


import microbank.common.util.SnowFlakeGen;
import microbank.transaction_service.dto.TransactionReqDto;
import microbank.transaction_service.dto.TransactionResDto;
import microbank.transaction_service.entity.TransactionEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TransactionUtils {
    @Value("${snowflake.node-id}")
    private long nodeId;

    private final SnowFlakeGen snowFlakeGen = new SnowFlakeGen(nodeId);

    public TransactionUtils() {
    }

    public String generateTransactionId() {
        return "TRANSACTION-" + snowFlakeGen.createEntityId();
    }

    public TransactionEntity mapFromDto(TransactionReqDto dto) {
        return TransactionEntity
                .builder()
                .transactionId(generateTransactionId())
                .amount(dto.getAmount())
                .createdAt(Instant.now())
                .transactionType(dto.getTransactionType())
                .description(dto.getDescription())
                .toAccountId(dto.getToAccountId())
                .fromAccountId(dto.getFromAccountId())
                .transactionType(dto.getTransactionType())
                .build();
    }

    public TransactionResDto mapFromEntity(TransactionEntity entity) {
        return TransactionResDto.builder()
                .status(entity.getStatus())
                .amount(entity.getAmount())
                .createdAt(entity.getCreatedAt())
                .transactionType(entity.getTransactionType())
                .processedAt(entity.getProcessedAt())
                .failureReason(entity.getFailureReason())
                .fromAccountId(entity.getFromAccountId())
                .toAccountId(entity.getToAccountId())
                .transactionReference(entity.getTransactionCode())
                .build();
    }

}

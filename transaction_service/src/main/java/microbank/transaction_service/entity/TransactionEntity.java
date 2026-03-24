package microbank.transaction_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import microbank.transaction_service.enums.TransactionStatus;
import microbank.transaction_service.enums.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "Transactions")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionEntity {
    @Id
    private String transactionId;

    @Column
    private TransactionType transactionType;

    @Column(unique = true, nullable = false)
    private String transactionCode;

    @Column
    private String refTransactionId;

    @Column(unique = true)
    private String idempotencyKey;

    @Column
    private String fromAccountId;

    @Column
    private String toAccountId;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant processedAt;

    @Column
    private TransactionStatus status;

    @Column(nullable = false)
    private BigDecimal amount;

//    @Column private String currency;

    @Column
    private String description;

    @Column
    private String failureReason;
}

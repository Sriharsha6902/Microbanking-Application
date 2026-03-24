package microbank.ledger_service.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import microbank.ledger_service.data.enums.EntryType;

import java.math.BigDecimal;

@Table(name = "Ledger")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LedgerEntity {

    @Id
    private String ledgerEntryId;

    @Column(nullable = false) private String accountId;

    @Column(nullable = false) private String transactionId;

    @Column(nullable = false) private EntryType entryType;

    @Column(nullable = false) private BigDecimal amount;

    @Column(nullable = false) private String description;

    @Column(nullable = false) private String entryAt;

}

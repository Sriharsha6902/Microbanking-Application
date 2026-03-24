package microbank.transaction_service.enums;

import lombok.Getter;

@Getter
public enum TransactionType {
    TRANSFER("TRANSFER"),
    WITHDRAWAL("WITHDRAWAL"),
    DEPOSIT("DEPOSIT");

    private final String value;

    TransactionType(String value) {
        this.value = value;
    }
}

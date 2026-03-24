package microbank.transaction_service.enums;

import lombok.Getter;

@Getter
public enum TransactionStatus {
    PENDING("PENDING"),
    SUCCESS("SUCCESS"),
    FAILED("FAILED"),
    REVERSED("REVERSED");

    private final String value;

    TransactionStatus(String value) {
        this.value = value;
    }
}

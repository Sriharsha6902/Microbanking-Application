package microbank.ledger_service.data.enums;

import lombok.Getter;

@Getter
public enum EntryType {
    DEBIT("DEBIT"),
    CREDIT("CREDIT");

    private String value;

    EntryType(String value) {}
}

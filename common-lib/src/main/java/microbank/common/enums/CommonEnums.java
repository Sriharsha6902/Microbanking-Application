package microbank.common.enums;

import lombok.Getter;

@Getter
public enum CommonEnums {
    ACCOUNT_CREATED("ACCOUNT_CREATED"),
    ACCOUNT_UPDATED("ACCOUNT_UPDATED"),
    ACCOUNT_DELETED("ACCOUNT_DELETED"),
    ACCOUNT_TOPIC("account");

    public final String value;

    CommonEnums(String value) {
        this.value = value;
    }
}

package microbank.transaction_service.exceptions;

import lombok.Getter;

@Getter
public class TransactionCreationException extends RuntimeException {
    public TransactionCreationException(String message) {
        super(message);
    }
}

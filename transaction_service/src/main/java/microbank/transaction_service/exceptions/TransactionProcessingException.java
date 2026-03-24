package microbank.transaction_service.exceptions;

public class TransactionProcessingException extends RuntimeException {
    public TransactionProcessingException(String message) {
        super(message);
    }
}

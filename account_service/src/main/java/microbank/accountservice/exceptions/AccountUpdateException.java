package microbank.accountservice.exceptions;

public class AccountUpdateException extends RuntimeException {
    public AccountUpdateException(String message) {
        super(message);
    }
}

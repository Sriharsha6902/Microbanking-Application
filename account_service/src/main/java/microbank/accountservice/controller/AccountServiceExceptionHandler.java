package microbank.accountservice.controller;

import microbank.accountservice.dtos.AccountDto;
import microbank.accountservice.exceptions.AccountAlreadyExistsException;
import microbank.accountservice.exceptions.AccountNotFoundException;
import microbank.accountservice.exceptions.AccountUpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccountServiceExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<AccountDto> handleNoCustomerFoundException(AccountNotFoundException ex) {
        AccountDto accountDto = new AccountDto();
        accountDto.setSuccess(false);
        accountDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(accountDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ResponseEntity<AccountDto> handleAccountAlreadyExistsException(AccountAlreadyExistsException ex) {
        AccountDto accountDto = new AccountDto();
        accountDto.setSuccess(false);
        accountDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(accountDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AccountUpdateException.class)
    public ResponseEntity<AccountDto> handleAccountUpdateException(AccountUpdateException ex) {
        AccountDto accountDto = new AccountDto();
        accountDto.setSuccess(false);
        accountDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(accountDto, HttpStatus.CONFLICT);
    }
}

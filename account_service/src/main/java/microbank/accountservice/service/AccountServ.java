package microbank.accountservice.service;

import lombok.RequiredArgsConstructor;
import microbank.accountservice.dtos.AccountDto;
import microbank.accountservice.entity.AccountEntity;
import microbank.accountservice.exceptions.AccountAlreadyExistsException;
import microbank.accountservice.exceptions.AccountNotFoundException;
import microbank.accountservice.exceptions.AccountUpdateException;
import microbank.accountservice.repository.AccountRepo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountServ {
    private final AccountRepo accountRepo;

    public AccountDto createAccount(AccountEntity accountEntity) {
        AccountDto accountDto = new AccountDto();
        try {
            this.accountRepo.save(accountEntity);
        } catch (DataIntegrityViolationException ex) {
            throw new AccountAlreadyExistsException("Account already exists with this email address");
        }
        accountDto.setName(accountEntity.getFirstName() + " " + accountEntity.getLastName());
        accountDto.setMessage("Account Created");
        accountDto.setSuccess(true);
        return accountDto;
    }

    public AccountDto updateAccount(AccountEntity accountEntity) {
        AccountDto accountDto = new AccountDto();
        try {
            this.accountRepo.save(accountEntity);
        } catch (DataIntegrityViolationException e) {
            throw new AccountUpdateException("Cannot update account with the given details");
        }
        accountDto.setSuccess(true);
        accountDto.setMessage("Account Updated");
        accountDto.setName(accountEntity.getFirstName() + " " + accountEntity.getLastName());
        return accountDto;
    }

    public AccountDto deleteAccount(AccountEntity accountEntity) {
        AccountDto accountDto = new AccountDto();
        try {
            this.accountRepo.delete(accountEntity);
        } catch (Exception e) {
            throw new AccountUpdateException("Cannot delete account with the given details");
        }
        accountDto.setSuccess(true);
        accountDto.setMessage("Account Deleted");
        return accountDto;
    }

    public AccountDto getAccount(AccountEntity accountEntity) {
        AccountDto accountDto = new AccountDto();
        Optional<AccountEntity> accountEntity1 = this.accountRepo.findById(accountEntity.getId());
        if (accountEntity1.isEmpty()) {
            throw new AccountNotFoundException("Account not found");
        }
        accountDto.setName(accountEntity1.get().getFirstName() + " " + accountEntity1.get().getLastName());
        accountDto.setSuccess(true);
        return accountDto;
    }
}

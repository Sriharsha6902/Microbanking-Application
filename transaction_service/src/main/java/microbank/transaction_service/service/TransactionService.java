package microbank.transaction_service.service;

import lombok.RequiredArgsConstructor;
import microbank.transaction_service.dto.TransactionResDto;
import microbank.transaction_service.entity.TransactionEntity;
import microbank.transaction_service.exceptions.TransactionCreationException;
import microbank.transaction_service.exceptions.TransactionProcessingException;
import microbank.transaction_service.repository.TransactionRepo;
import microbank.transaction_service.util.TransactionUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransactionService {
    private final TransactionRepo transactionRepo;
    private final TransactionUtils transactionUtils;

    public void createTransaction(TransactionEntity transactionEntity) throws TransactionCreationException {
        try {
            transactionRepo.save(transactionEntity);
        } catch (Exception e) {
            throw new TransactionCreationException(e.getMessage());
        }
    }

    private void creditAmount(TransactionEntity transactionEntity) {
    }

    private void debitAmount(TransactionEntity transactionEntity) {
    }

    public TransactionResDto transferAmount(TransactionEntity transactionEntity) throws TransactionProcessingException {
        try {
            this.debitAmount(transactionEntity);
            this.creditAmount(transactionEntity);
        } catch (Exception e) {
            throw new TransactionProcessingException(e.getMessage());
        }
        return this.transactionUtils.mapFromEntity(transactionEntity);
    }

    public TransactionResDto withdrawAmount(TransactionEntity transactionEntity) throws TransactionProcessingException {
        try {
            this.debitAmount(transactionEntity);
        } catch (Exception e) {
            throw new TransactionProcessingException(e.getMessage());
        }
        return this.transactionUtils.mapFromEntity(transactionEntity);
    }

    public TransactionResDto depositAmount(TransactionEntity transactionEntity) throws TransactionProcessingException {
        try {
            this.creditAmount(transactionEntity);
        } catch (Exception e) {
            throw new TransactionProcessingException(e.getMessage());
        }
        return this.transactionUtils.mapFromEntity(transactionEntity);
    }
}

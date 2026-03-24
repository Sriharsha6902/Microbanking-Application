package microbank.transaction_service.repository;

import microbank.transaction_service.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends CrudRepository<TransactionEntity, String> {
}

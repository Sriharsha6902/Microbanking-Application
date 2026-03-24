package microbank.ledger_service.repository;

import microbank.ledger_service.data.LedgerEntity;
import org.springframework.data.repository.CrudRepository;

public interface LedgerRepo extends CrudRepository<LedgerEntity, String> {
}

package microbank.accountservice.repository;

import microbank.accountservice.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepo extends CrudRepository<AccountEntity, UUID> {
}

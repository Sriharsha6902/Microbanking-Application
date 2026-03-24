package microbank.accountservice.repository;

import microbank.accountservice.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends CrudRepository<AccountEntity, String> {
}

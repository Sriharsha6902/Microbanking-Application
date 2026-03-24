package microbank.gateway_service.security.repository;

import microbank.gateway_service.security.entity.SessionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.security.core.userdetails.User;
import reactor.core.publisher.Mono;

public interface SecurityRepo extends ReactiveCrudRepository<SessionEntity, Long> {
    Mono<Boolean> existsSessionEntitiesByRefreshToken(String refreshToken);

    Mono<SessionEntity> findByUsername(String username);
}

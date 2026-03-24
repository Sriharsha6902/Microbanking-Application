package microbank.gateway_service.security.service;

import lombok.RequiredArgsConstructor;
import microbank.gateway_service.security.entity.SessionEntity;
import microbank.gateway_service.security.repository.SecurityRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final SecurityRepo securityRepo;

    public Mono<SessionEntity> getCurrentSession(String username) {
        return this.securityRepo.findByUsername(username);
    }
}

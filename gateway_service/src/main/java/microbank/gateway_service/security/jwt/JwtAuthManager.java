package microbank.gateway_service.security.jwt;

import lombok.RequiredArgsConstructor;
import microbank.gateway_service.security.jwt.util.JwtUtil;
import microbank.gateway_service.security.service.SecurityService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class JwtAuthManager implements ReactiveAuthenticationManager {

    private final JwtUtil jwtUtil;
    private final SecurityService securityService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();


        return null;
    }

    public ServerAuthenticationConverter authenticationConverter() {
        return exchange -> {
            String header = exchange.getRequest().getHeaders().getFirst("Authorization");

            if (header != null && header.startsWith("Bearer ")) {
                String token = header.substring(7);

                return Mono.just(
                        new UsernamePasswordAuthenticationToken(
                                null,
                                token
                        )
                );
            }

            return Mono.empty();
        };
    }
}
package microbank.gateway_service.security;

import lombok.RequiredArgsConstructor;
import microbank.gateway_service.security.jwt.JwtAuthManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

@RequiredArgsConstructor
@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

    public static final String LOGIN = "/login";

    private final JwtAuthManager authenticationManager;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(authenticationManager);
        authenticationWebFilter.setServerAuthenticationConverter(authenticationManager.authenticationConverter());
        return http
                .authorizeExchange(authorizeExchangeSpec -> {
                    authorizeExchangeSpec.anyExchange().authenticated();
                    authorizeExchangeSpec.pathMatchers(LOGIN).permitAll();
                }).formLogin(formLoginSpec -> formLoginSpec.loginPage(LOGIN))
                .addFilterBefore(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .logout(logoutSpec -> logoutSpec.logoutUrl("/home"))
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

package microbank.gateway_service.security.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    private SecretKey signingKey;

    @PostConstruct
    public void init() {
        this.signingKey = Jwts.SIG.HS256.key().build();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build().parseSignedClaims(token).getPayload();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        Date now = new Date(System.currentTimeMillis());
        return createToken(claims, username, now);
    }

    private String createToken(Map<String, Object> claims, String subject, Date issuedAt) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(issuedAt)
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
                .signWith(signingKey)
                .compact();
    }

    private String createRefreshToken(Map<String, Object> claims, String subject, Date issuedAt) {
        return Jwts.builder()
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .subject(subject)
                .signWith(signingKey)
                .issuedAt(issuedAt)
                .compact();
    }

    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}

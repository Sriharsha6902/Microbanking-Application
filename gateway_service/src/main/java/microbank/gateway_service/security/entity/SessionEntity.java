package microbank.gateway_service.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "Sessions")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long sessionId;

    @Column(nullable = false, unique = true)
    private String refreshToken;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false) private Date issuedAt;

    @Column(nullable = false) private Date expiresAt;

    @Column private Boolean active;
}

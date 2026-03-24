package microbank.accountservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "Accounts")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountEntity {

    @Id
    @Column(name = "AccountID")
    private String accountId;

    @Column(name = "Username", unique = true, nullable = false)
    private String username;

    @Column(name = "First Name", nullable = false)
    private String firstName;

    @Column(name = "Last Name", nullable = false)
    private String lastName;

    @Column(name = "Contact Number", nullable = false)
    private String contactNo;

    @Column(name = "Alternate Contact Number")
    private String alternateContactnNo;

    @Column(name = "Email", unique = true, nullable = false)
    private String email;

    @Column
    private BigDecimal balance;
}

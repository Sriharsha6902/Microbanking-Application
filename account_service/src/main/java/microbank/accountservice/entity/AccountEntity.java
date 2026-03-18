package microbank.accountservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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

    @Column(name = "User_pa")
    private String password;
}

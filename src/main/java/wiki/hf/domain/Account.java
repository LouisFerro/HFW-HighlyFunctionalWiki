// TODO: Implement.

package wiki.hf.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

@Entity
@Table(name = "Account")
public class Account extends AbstractPersistable<Long>
{
    private String fullName;
    private @NotNull @NotEmpty @Column(length = 64) String username;
    private @NotNull @NotEmpty @Column(length = 64) String password;
    private @Embedded Change change;
    private @Enumerated(EnumType.STRING) AccountType accountType;
}

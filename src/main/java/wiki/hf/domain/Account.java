// TODO: Implement.

package wiki.hf.domain;

import jakarta.persistence.*;
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
    private String username;
    private String password;
    private @Embedded Change change;
    private @Enumerated(EnumType.STRING) AccountType accountType;
}

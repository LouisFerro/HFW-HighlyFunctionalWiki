// TODO: Implement many-to-many relationships in respective entities.

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
    private @NotNull @NotEmpty @Column(length = 128) String fullName;
    private @NotNull @NotEmpty @Column(length = 128) String username;
    private @NotNull @NotEmpty @Column(length = 128) String password;
    private @Embedded Change change;
    private @Column(length = 1) AccountType accountType;
}

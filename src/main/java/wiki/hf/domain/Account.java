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
    private @NotNull @Embedded Action action;

    @Column(columnDefinition = "CHAR(1) CHECK(accountType in ('U', 'E', 'A', 'O')")
    private AccountType accountType;
}

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
    private @Column(length = 265) String fullName;
    private @NotBlank @Column(length = 128, nullable = false, unique = true) String username;
    private @NotBlank @Column(length = 128, nullable = false) String password;
    private @NotNull @Embedded Action action;

    @NotNull @Column(nullable = false, columnDefinition = "varchar(1) check(account_type in ('U', 'E', 'A', 'O'))")
    private AccountType accountType;
}

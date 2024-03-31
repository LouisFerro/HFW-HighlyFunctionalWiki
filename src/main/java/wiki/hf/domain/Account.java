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
    @Column(length = 256)
    private String name;

    @NotBlank
    @Column(length = 128, nullable = false, unique = true)
    private String username;

    @NotBlank
    @Column(length = 128, nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false, columnDefinition = "varchar(256) check (account_type in ('OWNER', 'ADMINISTRATOR', 'EDITOR', 'READER'))")
    private AccountType accountType;

    //TODO: Implement recursive relation of actions for historiszation of accounts.
    //@NotNull @Embedded
    //private Action action;
}
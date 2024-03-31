package wiki.hf.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

@Embeddable
public class Action
{
    @NotNull
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Account account;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime alteration;

    @NotNull
    @Column(nullable = false, columnDefinition = "varchar(256) check (action_type in ('CREATION', 'UPDATE', 'DELETION'))")
    private ActionType actionType;
}


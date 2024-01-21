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
    @NotNull @OneToOne
    private Account account;

    @NotNull @PastOrPresent @Column(nullable = false, unique = true)
    private LocalDateTime alteration;

    // TODO: Check for minimum number of days(30) between creation and deletion
    @Future
    private LocalDateTime deletion;

    @NotNull @Column(nullable = false, columnDefinition = "varchar(1) check(action_type in ('C', 'E', 'D'))")
    private ActionType actionType;
}


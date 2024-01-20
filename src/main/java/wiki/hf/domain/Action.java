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
    @NotNull @PastOrPresent @Column(nullable = false, unique = true)
    private LocalDateTime date;

    @NotNull @Column(nullable = false, columnDefinition = "varchar(1) check(action_type in ('C', 'E', 'D'))")
    private ActionType ActionType;
}


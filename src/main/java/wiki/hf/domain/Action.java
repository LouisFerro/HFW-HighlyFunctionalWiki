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
    private @NotNull LocalDateTime date;

    @Column(columnDefinition = "CHAR(1) CHECK(ActionType in ('C', 'E', 'D')")
    private ActionType ActionType;
}


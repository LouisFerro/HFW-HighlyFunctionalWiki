// TODO: Implement as embeddable.

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
public class Change
{
    private @NotNull LocalDateTime date;
    private @Column(length = 1) ChangeType changeType;
}


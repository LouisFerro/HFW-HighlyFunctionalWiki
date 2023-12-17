// TODO: Implement as embeddable.

package wiki.hf.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

@Embeddable
public class Change
{
    private LocalDateTime date;
    private @Enumerated(EnumType.STRING) ChangeType type;
}


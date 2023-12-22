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

    @Column(columnDefinition = "CHAR(1) CHECK(changeType in ('C', 'E', 'D')")
    private ChangeType changeType;
}


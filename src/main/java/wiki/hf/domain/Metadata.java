package wiki.hf.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor

@MappedSuperclass
public abstract class Metadata extends AbstractPersistable<Long>
{
    private @NotNull @NotEmpty String name;
    private @NotNull String description;
    private @NotNull @Embedded Action Action;
}

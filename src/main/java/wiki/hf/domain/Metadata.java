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
    @NotBlank
    @Column(length = 265, nullable = false, unique = true)
    private String name;

    @Column(length = 4048)
    private String description;


    @NotNull @Embedded
    private Action action;
}
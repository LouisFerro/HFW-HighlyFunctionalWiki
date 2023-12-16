package wiki.hf.domain;

import jakarta.persistence.*;
import lombok.*;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor

@MappedSuperclass
public abstract class Metadata extends AbstractPersistable<Long>
{
    private String name;
    private String description;
    //private Change change;
}

package wiki.hf.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

@Entity
@Table(name = "Comment")
public class Comment extends AbstractPersistable<Long>
{
    private @NotNull @NotEmpty @Column(length = 16192) String text;

    @NotNull @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Page page;

    private @Embedded Action action;
}
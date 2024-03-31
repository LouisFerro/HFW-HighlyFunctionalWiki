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
    @NotNull @NotEmpty
    @Column(length = 16192)
    private String text;

    @NotNull @Embedded
    private Action action;

    @NotNull
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(foreignKey = @ForeignKey(foreignKeyDefinition = "commentPage"))
    private Item page;
}
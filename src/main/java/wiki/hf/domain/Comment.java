/* TODO: Implement

package wiki.hf.domain;

import jakarta.persistence.*;
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
    private @NotNull @NotEmpty @Embedded Change change;
}

*/
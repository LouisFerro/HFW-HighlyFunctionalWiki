/* TODO: Implement as embeddable.

package wiki.hf.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Date;

@Data
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)

@Entity
public class Change extends AbstractPersistable<Long>
{
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Enumerated(EnumType.STRING)
    private ChangeType type;
}
*/

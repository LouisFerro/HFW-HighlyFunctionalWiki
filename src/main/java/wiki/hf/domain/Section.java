/* TODO: Implement with oneToMany relationship.

package wiki.hf.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)

@Entity
@Table(name = "Section")
public class Section extends Metadata
{
    private @NotNull List<Item> items;

    @Builder
    public Section(String name, String description, List<Item> items, Action Action)
    {
        super(name, description, Action);
        this.items = items;
        this.Action = Action;
    }
}

*/
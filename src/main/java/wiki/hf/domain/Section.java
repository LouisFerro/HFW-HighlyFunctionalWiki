/* TODO: Implement

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
    private List<Item> items;

    /*
    @Builder
    public Section(String name, String description, List<Item> items, Change change)
    {
        super(name, description, change);
        this.items = items;
        this.change = change;
    }
    *

    @Builder
    public Section(String name, String description, List<Item> items)
    {
        super(name, description);
        this.items = items;
    }
}

*/
package wiki.hf.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor

@Entity
@Table(name = "Item")
public class Item extends Metadata
{
    private ItemType type;
    /*
    private Content content;

    @Builder
    public Item(String name, String description, ItemType type, Change change, Content content)
    {
        super(name, description, change);
        this.type = type;
        this.change = change;
        this.content = content;
    }
    */

    @Builder
    public Item(String name, String description, ItemType type)
    {
        super(name, description);
        this.type = type;
    }
}

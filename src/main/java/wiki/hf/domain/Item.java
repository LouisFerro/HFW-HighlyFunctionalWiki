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
    private Content content;

    @Builder
    public Item(String name, String description, Change change, ItemType type, Content content)
    {
        super(name, description, change);
        this.type = type;
        this.content = content;
    }
}

package wiki.hf.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)

@Entity
@Table(name = "Item")
public class Item extends Metadata
{
    private @Enumerated(EnumType.STRING) ItemType itemType;
    private @Embedded Content content;

    @Builder
    public Item(String name, String description, Change change, ItemType itemType, Content content)
    {
        super(name, description, change);
        this.itemType = itemType;
        this.content = content;
    }
}

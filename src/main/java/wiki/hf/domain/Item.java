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
    private @Enumerated(EnumType.STRING) ItemType type;
    private @Embedded Content content;

    @Builder
    public Item(String name, String description, Change change, ItemType type, Content content)
    {
        super(name, description, change);
        this.type = type;
        this.content = content;
    }
}

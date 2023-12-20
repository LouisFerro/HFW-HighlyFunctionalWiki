package wiki.hf.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)

@Entity
@Table(name = "Item")
public class Item extends Metadata
{
    private @Column(length = 1) ItemType itemType;
    private @NotNull @Embedded Content content;

    @Builder
    public Item(String name, String description, Change change, ItemType itemType, Content content)
    {
        super(name, description, change);
        this.itemType = itemType;
        this.content = content;
    }
}

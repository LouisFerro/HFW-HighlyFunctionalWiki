package wiki.hf.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)

@Entity
@Table(name = "Item")
public class Item extends Metadata
{
    private @NotNull @Embedded Content content;

    @NotNull @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Section section;

    @NotNull @Column(columnDefinition = "CHAR(1) CHECK(itemType in ('L', 'T', 'I', 'V')")
    private ItemType itemType;

    @Builder
    public Item(String name, String description, Action action, ItemType itemType, Section section, Content content)
    {
        super(name, description, action);
        this.itemType = itemType;
        this.section = section;
        this.content = content;
    }
}

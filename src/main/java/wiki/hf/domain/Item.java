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
    @ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn( foreignKey = @ForeignKey( foreignKeyDefinition = "itemPage" ))
    private Item page;

    @ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn( foreignKey = @ForeignKey( foreignKeyDefinition = "itemParent" ))
    private Item parent;

    @NotNull
    @Column( nullable = false, columnDefinition = "varchar(256) check (item_type in ('PAGE', 'SECTION', 'LIST', 'VIDEO', 'IMAGE', 'TEXT'))" )
    private ItemType itemType;

    @NotNull @Embedded
    private Content content;

    @Builder
    public Item(String name, String description, Action action, Item page, Item parent, ItemType itemType, Content content)
    {
        super(name, description, action);
        this.page = page;
        this.parent = parent;
        this.itemType = itemType;
        this.content = content;
    }
}
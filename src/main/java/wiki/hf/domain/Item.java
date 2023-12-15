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
}

package wiki.hf.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor

@Entity
@Table(name = "Section")
public class Section extends Metadata
{
    private List<Item> items;
}

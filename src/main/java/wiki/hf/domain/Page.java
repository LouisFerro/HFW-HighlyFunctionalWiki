/* TODO: Implement with oneToMany relationship.

package wiki.hf.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)

@Entity
@Table(name = "Page")
public class Page extends Metadata
{
    private List<Section> sections;
    private List<Comment> comments;

    @Builder
    public Page(String name, String description, List<Section> sections, List<Comment> comments, Change change)
    {
        super(name, description, change);
        this.sections = sections;
        this.comments = comments;
        this.change = change;
    }
}

*/
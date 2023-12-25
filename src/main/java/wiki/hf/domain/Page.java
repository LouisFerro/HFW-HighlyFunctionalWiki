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
    private @NotNull List<Section> sections;
    private @NotNull List<Comment> comments;

    @Builder
    public Page(String name, String description, List<Section> sections, List<Comment> comments, Action Action)
    {
        super(name, description, Action);
        this.sections = sections;
        this.comments = comments;
        this.Action = Action;
    }
}

*/
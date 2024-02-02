package wiki.hf.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)

@Entity
@Table(name = "Section")
public class Section extends Metadata
{
    @NotNull
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(foreignKey = @ForeignKey(foreignKeyDefinition = "sectionPage"))
    private Page page;

    @Builder
    public Section(String name, String description , Action action, Page page)
    {
        super(name, description, action);
        this.page = page;
    }
}
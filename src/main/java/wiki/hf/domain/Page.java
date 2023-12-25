package wiki.hf.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)

@Entity
@Table(name = "Page")
public class Page extends Metadata
{
    @Builder
    public Page(String name, String description, Action action)
    {
        super(name, description, action);
    }
}

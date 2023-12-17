// TODO: Implement as embeddable.

package wiki.hf.domain;

import jakarta.persistence.*;
import lombok.*;

import java.nio.Buffer;

@Data
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)

@Embeddable
public class Content extends Metadata
{
    private String text;
    private String image;

    @Builder
    public Content(String name, String description, Change change, String text, String image)
    {
        super(name, description, change);
        this.text = text;
        this.image = image;
    }
}

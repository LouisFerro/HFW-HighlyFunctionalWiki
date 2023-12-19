// TODO: Implement image as Buffer (or other proper data type).

package wiki.hf.domain;

import jakarta.persistence.*;
import lombok.*;

//import java.nio.Buffer;

@Data
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor

@Embeddable
public class Content
{
    private String text;
    private String image;
    //Private Buffer image
}
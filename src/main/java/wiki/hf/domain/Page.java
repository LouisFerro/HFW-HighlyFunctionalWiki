package wiki.hf.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor

@Entity
@Table(name = "Page")
public class Page extends Metadata
{
    private List<Section> sections;
    private List<Comment> comments;
}

package wiki.hf.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

@Embeddable
public class Content
{
    private String text;
    private @Lob @Column(length = 1000) byte[] image;
}
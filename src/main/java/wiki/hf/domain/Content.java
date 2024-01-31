package wiki.hf.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

@Embeddable
public class Content
{
    @NotBlank
    private String text;

    @Lob
    @Column(length = 1000)
    private byte[] image;
}
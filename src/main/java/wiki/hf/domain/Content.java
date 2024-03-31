package wiki.hf.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

@Embeddable
public class Content
{
    @NotBlank
    @Column(length = 16192)
    private String text;

    @Lob
    @Column(length = 1000)
    private byte[] image;
}
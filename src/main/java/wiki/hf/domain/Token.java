package wiki.hf.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "Token")
public class Token extends AbstractPersistable<Long> {

    @NotBlank
    @Column(unique = true, nullable = false)
    private String value;

    @NotNull
    private TokenType type;
}
package wiki.hf.presentation.api.commands;

import jakarta.validation.constraints.*;

public record AccountCommand(
        @NotBlank String name,
        @NotBlank String username,
        @NotBlank String password ) {

}
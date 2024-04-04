package wiki.hf.presentation.forms;

import jakarta.validation.constraints.NotBlank;

public record AccountForm (@NotBlank String name, @NotBlank String username, @NotBlank String password, @NotBlank String passwordRepeat) {

    public static AccountForm base() {
        return new AccountForm("", "", "", "");
    }
}
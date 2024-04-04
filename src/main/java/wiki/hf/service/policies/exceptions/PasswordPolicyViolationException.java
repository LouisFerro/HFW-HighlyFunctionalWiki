package wiki.hf.service.policies.exceptions;

public class PasswordPolicyViolationException extends RuntimeException {

    public final String type;

    private PasswordPolicyViolationException(String message, String type) {
        super(message);
        this.type = type;
    }

    public static PasswordPolicyViolationException MissingCharacters() {
        return new PasswordPolicyViolationException("The password provided doesn't have 12 or more characters.", "MissingCharacters");
    }

    public static PasswordPolicyViolationException MissingDigit() {
        return new PasswordPolicyViolationException("The password provided is missing a digit.", "MissingDigit");
    }

    public static PasswordPolicyViolationException MissingLowercase() {
        return new PasswordPolicyViolationException("The password provided is missing a lowercase letter.", "MissingLowercase");
    }

    public static PasswordPolicyViolationException MissingUppercase() {
        return new PasswordPolicyViolationException("The password provided is missing a uppercase letter.", "MissingUppercase");
    }

    public static PasswordPolicyViolationException InvalidOrMissingSpecialCharacter() {
        return new PasswordPolicyViolationException("The password provided is missing or has an invalid special character.", "InvalidOrMissingSpecialCharacter");
    }
}

package wiki.hf.service.exceptions;

public class PasswordPolicyException extends BaseException {

    public final String type;

    private PasswordPolicyException(String type, String message) {
        super(PasswordPolicyException.class, type, message);
        this.type = type;
    }

    public static PasswordPolicyException MissingCharacters() {
        return new PasswordPolicyException("MissingCharacters", "The password provided doesn't have 12 or more characters.");
    }

    public static PasswordPolicyException MissingDigit() {
        return new PasswordPolicyException("MissingDigit", "The password provided is missing a digit.");
    }

    public static PasswordPolicyException MissingLowercaseLetter() {
        return new PasswordPolicyException("MissingLowercase", "The password provided is missing a lowercase letter.");
    }

    public static PasswordPolicyException MissingUppercaseLetter() {
        return new PasswordPolicyException("MissingUppercase", "The password provided is missing an uppercase letter.");
    }

    public static PasswordPolicyException InvalidOrMissingSpecialCharacter() {
        return new PasswordPolicyException("InvalidOrMissingSpecialCharacter", "The password provided is missing or has an invalid special character.");
    }
}
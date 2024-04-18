package wiki.hf.service.policies;

import lombok.extern.slf4j.Slf4j;
import wiki.hf.service.exceptions.*;

import org.springframework.beans.factory.annotation.*;

@Slf4j
public class PasswordPolicy {

    // @Value("${password.policy.constraint.length}")
    private static final int length = 12;

    // @Value("${password.policy.constraint.digit}")
    private static final String digit = ".*[0-9].*";

    // @Value("${password.policy.constraint.lowercase}")
    private static final String lowercase = ".*[a-z].*";

    // @Value("${password.policy.constraint.uppercase}")
    private static final String uppercase = ".*[A-Z].*";

    // @Value("${password.policy.constraint.specialCharacters}")
    private static final String specialCharacters = ".*[,.:;!?`´'\"+\\-*^%=()$€\\[\\]<>{}|\\\\/_~@#&§°].*";

    public static void check(String password) {
        if (password == null || password.isBlank() || password.length() < length) throw PasswordPolicyException.MissingCharacters();
        if (!password.matches(digit)) throw PasswordPolicyException.MissingDigit();
        if (!password.matches(lowercase)) throw PasswordPolicyException.MissingLowercaseLetter();
        if (!password.matches(uppercase)) throw PasswordPolicyException.MissingUppercaseLetter();
        if (!password.matches(specialCharacters)) throw PasswordPolicyException.InvalidOrMissingSpecialCharacter();
    }
}
package wiki.hf.service.policies;

import wiki.hf.service.policies.exceptions.*;

import org.springframework.beans.factory.annotation.*;

public class PasswordPolicy {

    @Value("${password.policy.constraint.length}")
    private static int length;

    @Value("${password.policy.constraint.digit}")
    private static String digit;

    @Value("${password.policy.constraint.lowercase}")
    private static String lowercase;

    @Value("${password.policy.constraint.uppercase}")
    private static String uppercase;

    @Value("${password.policy.constraint.specialCharacters}")
    private static String specialCharacters;

    public static void check(String password) {
        if(password == null || password.isBlank() || password.length() < length) throw PasswordPolicyViolationException.MissingCharacters();
        if(!password.matches(digit)) throw PasswordPolicyViolationException.MissingDigit();
        if(!password.matches(lowercase)) throw PasswordPolicyViolationException.MissingLowercase();
        if(!password.matches(uppercase)) throw PasswordPolicyViolationException.MissingUppercase();
        if(!password.matches(specialCharacters)) throw PasswordPolicyViolationException.InvalidOrMissingSpecialCharacter();
    }
}
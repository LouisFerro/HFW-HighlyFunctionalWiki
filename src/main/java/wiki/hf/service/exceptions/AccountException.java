package wiki.hf.service.exceptions;

import wiki.hf.domain.Account;

public class AccountException extends BaseException {

    public AccountException(String type, Object object, String detail) { super(Account.class, type, object, detail); }
    public AccountException(String type, String detail) { super(Account.class, type, detail); }

    public static AccountException NoName() { return new AccountException("NoName", "For this account, a name is required."); }
    public static AccountException NoUsername() { return new AccountException("NoUsername", "For this account, a username is required."); }

    public static AccountException NoAccountExists(Object object) {
        String message = "No accounts exist with %s".formatted(object instanceof Long ? "id %s".formatted(object) : "username %s".formatted(object));

        return new AccountException("NoAccountExists", object, message);
    }

    public static AccountException WrongPassword(Object object, String password) {
        String message = "Account with %s doesn't have the password %s".formatted(object instanceof Long ? "id %s".formatted(object) : "username %s".formatted(object), password);

        return new AccountException("WrongPassword", object, message);
    }

    public static AccountException AccountExists(String username) {
        return new AccountException("AccountExists", username, "An account with username %s already exists".formatted(username));
    }

    public static AccountException WrongRepeatedPassword() {
        return new AccountException("WrongRepeatedPassword", "Passwords do not match");
    }
}

package wiki.hf.service.exceptions;

import wiki.hf.domain.Account;

import java.util.*;

public class AccountNoContentException extends BaseException {

    public AccountNoContentException(String username, String detail) {
        super(Account.class, username, detail);
    }

    public static AccountNoContentException UsernameAndPassword(String username, String password) {
        Objects.requireNonNull(username, password);
        return new AccountNoContentException(username, "No Accounts exist with username %s and password %s".formatted(username, password));
    }
}

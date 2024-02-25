package wiki.hf.service.exceptions;

import wiki.hf.domain.Account;

import java.util.*;


// TODO: Adapt for restful usage.
public class AccountNotFoundException extends BaseException {

    public AccountNotFoundException(String username, String detail) {
        super(Account.class, username, detail);
    }

    public static AccountNotFoundException username(String username) {
        Objects.requireNonNull(username);
        return new AccountNotFoundException(username, "Account with username %s not found.".formatted(username));
    }
}

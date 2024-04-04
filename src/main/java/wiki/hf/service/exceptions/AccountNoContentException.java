package wiki.hf.service.exceptions;

import wiki.hf.domain.Account;

import java.util.*;

public class AccountNoContentException extends BaseException {

    public AccountNoContentException(String detail, Object object) {
        super(Account.class, detail, object);
    }

    public static AccountNoContentException UsernameAndPassword(String username, String password) {
        return new AccountNoContentException("No Accounts exist with username %s and password %s".formatted(username, password), username);
    }

    public static AccountNoContentException IdAndPassword(Long id, String password) {
        return new AccountNoContentException("No Accounts exist with id %s and password %s".formatted(id, password), id);
    }
}

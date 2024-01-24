package wiki.hf.presentation.api.dto;

import wiki.hf.domain.Account;

public record AccountDTO(String fullName, String username, String password, String accountType) {
    public AccountDTO(Account account) {
        this(account.getFullName(), account.getUsername(), account.getPassword(), String.valueOf(account.getAccountType()));
    }
}

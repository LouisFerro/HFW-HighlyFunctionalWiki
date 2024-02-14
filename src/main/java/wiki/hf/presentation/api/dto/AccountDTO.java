package wiki.hf.presentation.api.dto;

import wiki.hf.domain.Account;

public record AccountDTO(String name, String username, String password, String accountType) {
    public AccountDTO(Account account) {
        this(account.getName(), account.getUsername(), account.getPassword(), String.valueOf(account.getAccountType()));
    }
}

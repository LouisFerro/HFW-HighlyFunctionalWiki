package wiki.hf.presentation.dataTransferObjects;

import wiki.hf.domain.Account;

public record AccountResult(String name,
                            String username,
                            String password,
                            String accountType) {

    public AccountResult (Account account) {
        this(account.getName(), account.getUsername(), account.getPassword(), String.valueOf(account.getAccountType()));
    }

    public AccountResult (AccountRequest accountRequest) {
        this(accountRequest.name(), accountRequest.username(), accountRequest.password(), String.valueOf(accountRequest.accountType()));
    }
}
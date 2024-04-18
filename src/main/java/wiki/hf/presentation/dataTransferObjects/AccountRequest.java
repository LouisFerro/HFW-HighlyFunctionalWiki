package wiki.hf.presentation.dataTransferObjects;

import wiki.hf.domain.*;

import lombok.*;

@Builder
public record AccountRequest(String name,
                             String username,
                             String password,
                             String repeatedPassword,
                             AccountType accountType) {

    public static AccountRequest Base () {
        return new AccountRequest("", "", "", "", AccountType.READER);
    }

    public static AccountRequest New (Account account) {
        return new AccountRequest(account.getName(), account.getUsername(), account.getPassword(), account.getPassword(), account.getAccountType());
    }
}
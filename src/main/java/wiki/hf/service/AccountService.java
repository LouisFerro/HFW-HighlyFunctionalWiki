package wiki.hf.service;

import wiki.hf.domain.*;
import wiki.hf.foundation.*;
import wiki.hf.persistence.repositories.*;
import wiki.hf.presentation.dataTransferObjects.AccountRequest;
import wiki.hf.service.exceptions.*;
import wiki.hf.service.policies.*;

import org.springframework.stereotype.Service;

import lombok.*;
import lombok.extern.log4j.*;

import java.util.*;

@RequiredArgsConstructor
@Log4j2

@Service
public class AccountService implements LikeFormat {

    private final AccountRepository repository;

    public List<Account> findAllByName(Optional<String> name, Optional<String> username) {
        log.debug("Finding all accounts by name: {} or username: {}", name, username);

        return name.map(this::formatExpression)
                   .map(repository::findAllByNameLikeIgnoreCase)
                   .orElse(username.map(this::formatExpression)
                                   .map(repository::findAllByUsernameLikeIgnoreCase)
                                   .orElseGet(repository::findAll));
    }

    public Account findByUsernameAndPassword(String username, String password) {
        log.debug("Checking account by username: {} and password: {}", username, password);

        repository.findByUsernameIgnoreCase(username)
                  .orElseThrow(() -> AccountException.NoAccountExists(username));

        return repository.findByUsernameIgnoreCaseAndPassword(username, password)
                         .orElseThrow(() -> AccountException.WrongPassword(username, password));
    }

    public Boolean checkByIdAndPassword(Long id, String password) {
        log.debug("Checking account by id: {} and password: {}", id, password);

        var account = repository.existsById(id);

        if (!account) throw AccountException.NoAccountExists(id);
        else {
            account = repository.existsByIdAndPassword(id, password);

            if (!account) throw AccountException.WrongPassword(id, password);
        }

        return true;
    }

    public Account update(AccountRequest accountRequest) {
        log.debug("Updating account: {}", accountRequest);
        Account account = repository.findByUsernameIgnoreCase(accountRequest.username())
                                    .orElseThrow(() -> AccountException.NoAccountExists(accountRequest.username()));

        if (accountRequest.name().isBlank()) throw AccountException.NoName();
        if (accountRequest.username().isBlank()) throw AccountException.NoUsername();

        PasswordPolicy.check(accountRequest.password());

        account.setName(accountRequest.name());
        account.setUsername(accountRequest.username());
        account.setPassword(accountRequest.password());
        account.setAccountType(accountRequest.accountType());

        log.info("Account {}, has been updated successfully", account);
        repository.save(account);
        return account;
    }

    public Account create(AccountRequest accountRequest) {
        log.debug("Saving account: {}", accountRequest);

        if (accountRequest.name().isBlank()) throw AccountException.NoName();
        if (accountRequest.username().isBlank()) throw AccountException.NoUsername();
        if (repository.existsByUsernameIgnoreCase(accountRequest.username())) throw AccountException.AccountExists(accountRequest.username());

        var account = Account.builder()
                             .name(accountRequest.name())
                             .username(accountRequest.username())
                             .password(accountRequest.password())
                             .accountType(AccountType.READER)
                             .build();

        PasswordPolicy.check(account.getPassword());
        if (!accountRequest.password().equals(accountRequest.repeatedPassword())) throw AccountException.WrongRepeatedPassword();

        log.info("Account {}, has been created successfully", account);
        repository.save(account);
        return account;
    }

    public void delete(Long id) { repository.deleteById(id); }
}
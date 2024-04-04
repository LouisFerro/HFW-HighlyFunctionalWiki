package wiki.hf.service;

import wiki.hf.domain.*;
import wiki.hf.foundation.*;
import wiki.hf.persistence.repositories.*;
import wiki.hf.service.exceptions.*;
import wiki.hf.service.policies.*;

import org.springframework.transaction.annotation.Transactional;
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
        return repository.findByUsernameIgnoreCaseAndPassword(username, password)
                         .orElseThrow(() -> AccountNoContentException.UsernameAndPassword(username, password));
    }

    public Boolean checkByIdAndPassword(Long id, String password) {
        var account = repository.existsByIdAndPassword(id, password);

        if(account) return true;
        else throw AccountNoContentException.IdAndPassword(id, password);
    }

    public void update(Account account) {
        repository.save(account);
    }

    public Account save(String name, String username, String password) {
        if(repository.existsByUsernameIgnoreCase(username)) {
            throw UserAlreadyExistsException.Username(username);
        }

        PasswordPolicy.check(password);
        Account account = Account.builder()
                                 .name(name)
                                 .username(username)
                                 .password(password)
                                 .accountType(AccountType.READER)
                                 .build();

        repository.save(account);
        return account;
    }

    public void delete(Long id) { repository.deleteById(id); }
}
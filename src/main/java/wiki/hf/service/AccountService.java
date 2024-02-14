package wiki.hf.service;

import wiki.hf.domain.Account;
import wiki.hf.foundation.LikeFormat;
import wiki.hf.persistence.repositories.AccountRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.*;

@RequiredArgsConstructor
@Log4j2

@Service
@Transactional(readOnly = true)
public class AccountService implements LikeFormat {

    private final AccountRepository repository;

    public List<Account> findAllByName(Optional<String> name, Optional<String> username) {
        return name.map(this::formatExpression)
                   .map(repository::findAllByNameLikeIgnoreCase)
                   .orElse(username.map(this::formatExpression)
                                   .map(repository::findAllByUsernameLikeIgnoreCase)
                                   .orElseGet(repository::findAll));
    }

    public Account findByUsername(String username) {
        return repository.findByUsernameIgnoreCase(username);
    }

    public Account findByUsernameAndPassword(String username, String password) {
        return repository.findByUsernameAndPasswordIgnoreCase(username, password);
    }
}
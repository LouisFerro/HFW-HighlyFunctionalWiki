package wiki.hf.service;

import org.springframework.transaction.annotation.Transactional;
import wiki.hf.domain.Account;
import wiki.hf.persistence.repositories.AccountRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository repository;

    public List<Account> findAll() {
        return repository.findAll();
    }

    public Account findByFullName(String fullName) {
        return repository.findByFullNameIgnoreCase(fullName);
    }

    public Account findByUsername(String username) {
        return repository.findByUsernameIgnoreCase(username);
    }

    public Account findByUsernameAndPassword(String username, String password) {
        return repository.findByUsernameAndPasswordIgnoreCase(username, password);
    }
}

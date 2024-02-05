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

    public List<Account> findAllByName(String fullName, String username) {
        if (fullName != null && username != null) {
            return repository.findAllByFullNameAndUsernameLikeIgnoreCase(fullName, username);
        } else if (fullName != null) {
            return repository.findAllByFullNameLikeIgnoreCase(fullName);
        } else if (username != null) {
            return repository.findAllByUsernameLikeIgnoreCase(username);
        } else {
            return repository.findAll();
        }
    }

    public Account findByUsername(String username) {
        return repository.findByUsernameIgnoreCase(username);
    }

    public Account findByUsernameAndPassword(String username, String password) {
        return repository.findByUsernameAndPasswordIgnoreCase(username, password);
    }
}

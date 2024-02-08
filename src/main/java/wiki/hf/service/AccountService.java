package wiki.hf.service;

import org.springframework.transaction.annotation.Transactional;
import wiki.hf.domain.Account;
import wiki.hf.foundation.LikeFormat;
import wiki.hf.persistence.repositories.AccountRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
@Transactional(readOnly = true)
public class AccountService implements LikeFormat {

    private final AccountRepository repository;

    public List<Account> findAllByName(Optional<String> fullName, Optional<String> username) {
       return fullName.map(this::formatExpression)
               .map(repository::findAllByFullNameLikeIgnoreCase)
               .orElse(username
                       .map(this::formatExpression)
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

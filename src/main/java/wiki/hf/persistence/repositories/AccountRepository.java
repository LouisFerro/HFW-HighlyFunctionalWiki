package wiki.hf.persistence.repositories;

import wiki.hf.domain.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>
{
    List<Account> findByFullNameLikeIgnoreCase(String fullName);

    List<Account> findByUsernameLikeIgnoreCase(String username);

    List<Account> findByFullNameAndUsernameLikeIgnoreCase(String fullName, String username);

    Account findByUsernameIgnoreCase(String username);

    Account findByUsernameAndPasswordIgnoreCase(String username, String password);
}
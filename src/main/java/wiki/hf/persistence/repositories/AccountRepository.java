package wiki.hf.persistence.repositories;

import wiki.hf.domain.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByNameLikeIgnoreCase(String name);

    List<Account> findAllByUsernameLikeIgnoreCase(String username);

    Optional<Account> findByUsernameAndPasswordIgnoreCase(String username, String password);
}
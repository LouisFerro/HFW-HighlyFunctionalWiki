package wiki.hf.persistence.repositories;

import wiki.hf.domain.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByNameLikeIgnoreCase(String name);

    List<Account> findAllByUsernameLikeIgnoreCase(String username);

    Optional<Account> findByUsernameIgnoreCase(String username);

    Optional<Account> findByUsernameIgnoreCaseAndPassword(String username, String password);

    Boolean existsByUsernameIgnoreCase(String username);

    Boolean existsByIdAndPassword(Long id, String password);
}
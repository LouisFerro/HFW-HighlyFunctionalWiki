package wiki.hf.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;
import wiki.hf.domain.*;

import java.util.Optional;

@Repository
public interface PageRepository extends JpaRepository<Page, Long>
{
    /* TODO: Check on CRUD repository
    @Override
    Optional<Page> findById(Long id);
    */

    Optional<Page> findByName(String name);

    /* TODO: Implement.
    Optional<Page> findByAction(Action action);
    */
}
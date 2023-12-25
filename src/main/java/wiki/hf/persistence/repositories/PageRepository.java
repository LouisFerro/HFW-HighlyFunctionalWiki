// TODO: Implement.

package wiki.hf.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wiki.hf.domain.*;

import java.util.Optional;

@Repository
public interface PageRepository extends JpaRepository<Page, Long>
{
    Optional<Page> findByName(String name);
}


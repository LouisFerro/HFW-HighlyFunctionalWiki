package wiki.hf.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import wiki.hf.domain.Page;

import java.util.Optional;

public interface PageRepository extends JpaRepository<Page, Long>
{
    Optional<Page> findByName(String name);
}

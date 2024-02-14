package wiki.hf.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;
import wiki.hf.domain.Section;

import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long>
{
    Optional<Section> findByName(String name);
}
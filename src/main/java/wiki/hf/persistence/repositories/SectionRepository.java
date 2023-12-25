package wiki.hf.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;
import wiki.hf.domain.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long>
{

}
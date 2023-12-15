package wiki.hf.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import wiki.hf.domain.Section;

public interface SectionRepository extends JpaRepository<Section, Long>
{

}

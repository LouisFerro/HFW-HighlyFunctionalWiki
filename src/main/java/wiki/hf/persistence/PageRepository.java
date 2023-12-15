package wiki.hf.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import wiki.hf.domain.Page;

public interface PageRepository extends JpaRepository<Page, Long>
{

}

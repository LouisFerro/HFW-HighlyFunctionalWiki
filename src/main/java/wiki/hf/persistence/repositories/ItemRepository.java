package wiki.hf.persistence.repositories;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import wiki.hf.domain.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>
{

}

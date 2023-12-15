package wiki.hf.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import wiki.hf.domain.Item;


public interface ItemRepository extends JpaRepository<Item, Long>
{

}

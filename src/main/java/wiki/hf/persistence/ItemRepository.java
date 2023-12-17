package wiki.hf.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wiki.hf.domain.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>
{

}

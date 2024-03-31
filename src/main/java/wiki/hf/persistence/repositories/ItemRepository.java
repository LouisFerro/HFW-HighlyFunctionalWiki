package wiki.hf.persistence.repositories;

import wiki.hf.domain.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByNameAndItemType(String name, ItemType itemType);

    List<Item> findAllByPage(Item page);
}

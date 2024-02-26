package wiki.hf.persistence.repositories;

import wiki.hf.domain.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>
{
    Optional<Item> findByName(String name);
}

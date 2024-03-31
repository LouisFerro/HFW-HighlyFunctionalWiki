package wiki.hf.service;

import wiki.hf.domain.*;
import wiki.hf.foundation.*;
import wiki.hf.persistence.repositories.*;
import wiki.hf.service.exceptions.*;
import wiki.hf.service.policies.*;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import lombok.*;
import lombok.extern.log4j.*;

import java.util.*;

@RequiredArgsConstructor
@Log4j2

@Service
public class ItemService {

    private final ItemRepository repository;

    public Optional<Item> findPageByName(String name) {
        return repository.findByNameAndItemType(name, ItemType.PAGE);
    }

    public List<Item> findItemsByPage(Item page) {
        return repository.findAllByPage(page);
    }
}

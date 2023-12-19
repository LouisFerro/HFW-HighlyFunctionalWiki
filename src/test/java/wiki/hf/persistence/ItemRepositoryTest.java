package wiki.hf.persistence;

import wiki.hf.domain.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ItemRepositoryTest
{
    @Autowired
    private ItemRepository repository;
    Item sword;

    @BeforeEach
    void Setup()
    {
        sword = Item.builder()
                   .name("Sword")
                   .description("A sword made of hardened steel.")
                   .build();
    }

    @Test
    void SavingItem()
    {
        var save = repository.saveAndFlush(sword);

        assertThat(save).isNotNull().isSameAs(sword);
        assertThat(save.getId()).isNotNull();
    }
}
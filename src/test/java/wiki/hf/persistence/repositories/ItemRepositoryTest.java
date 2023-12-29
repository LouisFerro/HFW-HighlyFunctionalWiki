package wiki.hf.persistence.repositories;

import wiki.hf.domain.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ItemRepositoryTest
{
    @Autowired
    private ItemRepository repository;
    Item sword;

    @BeforeEach
    void SetupItem()
    {
        sword = Item.builder()
                    .name("Sword")
                    .description("A sword made of hardened steel")
                    .action(Action.builder()
                                  .ActionType(ActionType.CREATE)
                                  .date(LocalDateTime.of(2023, 12, 20, 12, 0, 0))
                                  .build())
                    .content(Content.builder()
                                    .text("The sword is the most common weapon in the game.")
                                    .build())
                    .section(Section.builder()
                                    .name("Basic weapons.")
                                    .description("All the weapons that are unlocked at the start of the game.")
                                    .action(Action.builder()
                                                  .ActionType(ActionType.CREATE)
                                                  .date(LocalDateTime.of(2023, 12, 20, 12, 0, 0))
                                                  .build())
                                    .page(Page.builder()
                                              .name("Weapons")
                                              .description("The Weapons of the game.")
                                              .action(Action.builder()
                                                            .ActionType(ActionType.CREATE)
                                                            .date(LocalDateTime.of(2023, 12, 20, 12, 0, 0))
                                                            .build())
                                              .build())
                                    .build())
                    .itemType(ItemType.TEXT)
                    .build();

        repository.save(sword);
    }

    @Test
    void ReadItem()
    {
        assertThat(repository.save(sword)).isNotNull().isSameAs(sword);
        assertThat(repository.save(sword).getName()).isEqualTo("Sword");
        assertThat(repository.save(sword).getId()).isNotNull();
    }

    // TODO: Fix this test.
    @Test
    void FindItemByName()
    {
        assertThat(repository.findByName("Sword")).isPresent();
    }

    /*
    @Test
    void FindItemByAction()
    {

    }
    */
}
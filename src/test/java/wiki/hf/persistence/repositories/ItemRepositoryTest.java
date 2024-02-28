package wiki.hf.persistence.repositories;

import wiki.hf.TestContainerConfiguration;
import wiki.hf.domain.*;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

@DataJpaTest
@Import(TestContainerConfiguration.class)
class ItemRepositoryTest {

    private @Autowired ItemRepository repository;

    Action action;
    Item sword;

    @BeforeEach
    void SetupItem() {
        action = TestFixtures.Action(TestFixtures.Account());
        sword = Item.builder()
                .name("Sword")
                .description("A sword made of hardened steel")
                .action(action)
                .content(Content.builder()
                        .text("The sword is the most common weapon in the game.")
                        .build())
                .section(Section.builder()
                        .name("Basic weapons.")
                        .description("All the weapons that are unlocked at the start of the game.")
                        .action(action)
                        .page(Page.builder()
                                .name("Weapons")
                                .description("The Weapons of the game.")
                                .action(action)
                                .build())
                        .build())
                .itemType(ItemType.TEXT)
                .build();

        repository.save(sword);
    }

    @Test
    void ReadItem() {
        assertThat(repository.save(sword)).isNotNull().isSameAs(sword);
        assertThat(repository.save(sword).getName()).isEqualTo("Sword");
        assertThat(repository.save(sword).getId()).isNotNull();
    }

    @Test
    void FindItemByName() {
        assertThat(repository.findByName("Sword")).isPresent();
    }

    /*
    @Test
    void FindItemByAction()
    {

    }
    */
}
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

    private @Autowired AccountRepository accountRepository;
    private @Autowired ItemRepository itemRepository;

    Account account;
    Action action;
    Item sword;

    @BeforeEach
    void SetupItem() {
        account = TestFixtures.account();
        accountRepository.save(account);

        action = TestFixtures.action(account);
        sword = Item.builder()
                .name("Sword")
                .description("A sword made of hardened steel")
                .version("0.1.0")
                .action(action)
                .content(Content.builder()
                        .text("The sword is the most common weapon in the game.")
                        .build())
                .section(Section.builder()
                        .name("Basic weapons.")
                        .description("All the weapons that are unlocked at the start of the game.")
                        .version("0.1.0")
                        .action(action)
                        .page(Page.builder()
                                .name("Weapons")
                                .description("The Weapons of the game.")
                                .version("0.1.0")
                                .action(action)
                                .build())
                        .build())
                .itemType(ItemType.TEXT)
                .build();

        itemRepository.save(sword);
    }

    @Test
    void ReadItem() {
        assertThat(itemRepository.save(sword)).isNotNull().isSameAs(sword);
        assertThat(itemRepository.save(sword).getName()).isEqualTo("Sword");
        assertThat(itemRepository.save(sword).getId()).isNotNull();
    }

    @Test
    void FindItemByName() {
        assertThat(itemRepository.findByName("Sword")).isPresent();
    }

    /*
    @Test
    void FindItemByAction()
    {

    }
    */
}
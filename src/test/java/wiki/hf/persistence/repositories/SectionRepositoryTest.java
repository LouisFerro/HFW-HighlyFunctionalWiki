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
class SectionRepositoryTest {

    private @Autowired AccountRepository accountRepository;
    private @Autowired SectionRepository sectionRepository;

    Account account;
    Action action;
    Section section;

    @BeforeEach
    void SetUpSection() {
        account = TestFixtures.account();
        accountRepository.save(account);

        action = TestFixtures.action(account);
        section = Section.builder()
                .name("Basic weapons")
                .description("All the weapons that are unlocked at the start of the game")
                .version("0.1.0")
                .action(action)
                .page(Page.builder()
                        .name("Weapons")
                        .description("The Weapons of the game")
                        .version("0.1.0")
                        .action(action)
                        .build())
                .build();

        sectionRepository.save(section);
    }

    @Test
    void ReadSection() {
        assertThat(sectionRepository.save(section)).isNotNull().isSameAs(section);
        assertThat(sectionRepository.save(section).getName()).isEqualTo("Basic weapons");
        assertThat(sectionRepository.save(section).getId()).isNotNull();
    }

    @Test
    void FindSectionByName() {
        assertThat(sectionRepository.findByName("Basic weapons")).isPresent();
    }

    /*
    @Test
    void FindSectionByAction()
    {

    }
    */
}
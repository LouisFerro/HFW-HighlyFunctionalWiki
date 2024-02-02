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

    private @Autowired SectionRepository repository;

    Action action;
    Section section;

    @BeforeEach
    void SetUpSection() {
        action = TestFixtures.action(TestFixtures.account());
        section = Section.builder()
                .name("Basic weapons")
                .description("All the weapons that are unlocked at the start of the game")
                .action(action)
                .page(Page.builder()
                        .name("Weapons")
                        .description("The Weapons of the game")
                        .action(action)
                        .build())
                .build();

        repository.save(section);
    }

    @Test
    void ReadSection() {
        assertThat(repository.save(section)).isNotNull().isSameAs(section);
        assertThat(repository.save(section).getName()).isEqualTo("Basic weapons");
        assertThat(repository.save(section).getId()).isNotNull();
    }

    @Test
    void FindSectionByName() {
        assertThat(repository.findByName("Basic weapons")).isPresent();
    }

    /*
    @Test
    void FindSectionByAction()
    {

    }
    */
}
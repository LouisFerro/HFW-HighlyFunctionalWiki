package wiki.hf.persistence.repositories;

import wiki.hf.TestContainerConfiguration;
import wiki.hf.domain.*;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import static org.assertj.core.api.AssertionsForClassTypes.*;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

@DataJpaTest
@Import(TestContainerConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SectionRepositoryTest
{
    @Autowired
    private SectionRepository repository;
    Section section;

    @BeforeEach
    void SetupSection()
    {
        section = Section.builder()
                         .name("Basic weapons")
                         .description("All the weapons that are unlocked at the start of the game")
                         .action(Action.builder()
                                       .ActionType(ActionType.CREATE)
                                       .date(LocalDateTime.of(2023, 12, 20, 12, 0, 0))
                                       .build())
                         .page(Page.builder()
                                   .name("Weapons")
                                   .description("The Weapons of the game")
                                   .action(Action.builder()
                                                 .ActionType(ActionType.CREATE)
                                                 .date(LocalDateTime.of(2023, 12, 20, 12, 0, 0))
                                                 .build())
                                   .build())
                         .build();

        repository.save(section);
    }

    @Test
    void ReadSection()
    {
        assertThat(repository.save(section)).isNotNull().isSameAs(section);
        assertThat(repository.save(section).getName()).isEqualTo("Basic weapons");
        assertThat(repository.save(section).getId()).isNotNull();
    }

    // TODO: Fix this test.
    @Test
    void FindSectionByName()
    {
        assertThat(repository.findByName("Basic weapons")).isPresent();
    }

    /*
    @Test
    void FindSectionByAction()
    {

    }
    */
}
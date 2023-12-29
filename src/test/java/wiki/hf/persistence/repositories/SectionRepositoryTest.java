package wiki.hf.persistence.repositories;

import wiki.hf.domain.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDateTime;

@DataJpaTest
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
        assertThat(repository.findByName("Weapons")).isPresent();
    }

    /*
    @Test
    void FindSectionByAction()
    {

    }
    */
}
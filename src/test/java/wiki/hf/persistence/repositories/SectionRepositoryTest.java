package wiki.hf.persistence.repositories;

import wiki.hf.domain.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class SectionRepositoryTest
{
    @Autowired
    private SectionRepository repository;
    Section section;

    @BeforeEach
    void Setup()
    {
        section = Section.builder()
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
                         .build();
    }

    @Test
    void SaveAndReadSection()
    {
        var save = repository.save(section);

        assertThat(save).isNotNull().isSameAs(section);
        assertThat(save.getId()).isNotNull();
    }
}
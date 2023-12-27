package wiki.hf.persistence.repositories;

import wiki.hf.domain.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class PageRepositoryTest
{
    @Autowired
    private PageRepository repository;

    Page page;

    @BeforeEach
    void Setup()
    {
        page = Page.builder()
                       .name("Weapons")
                       .description("The Weapons of the game.")
                       .action(Action.builder()
                                     .ActionType(ActionType.CREATE)
                                     .date(LocalDateTime.of(2023, 12, 20, 12, 0, 0))
                                     .build())
                       .build();
    }

    @Test
    void SaveAndReadPage()
    {
        var save = repository.save(page);

        assertThat(save).isNotNull().isSameAs(page);
        assertThat(save.getId()).isNotNull();
    }

    // TODO: Fix this test.
    @Test
    void SaveAndFindPagesByName()
    {
        repository.save(page);

        var find = repository.findByName("Weapons");

        assertThat(find).isPresent();
    }

    /*
    @Test
    void FindPageByAction()
    {

    }
    */
}
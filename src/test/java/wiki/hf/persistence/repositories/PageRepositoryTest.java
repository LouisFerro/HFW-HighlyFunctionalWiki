// TODO: Implement.

package wiki.hf.persistence.repositories;

import wiki.hf.domain.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class PageRepositoryTest
{
    @Autowired
    private PageRepository repository;
    Page homepage;

    @BeforeEach
    void SetupTest()
    {
        homepage = Page.builder()
                       .name("Home")
                       .description("The landing page")
                       .action(Action.builder()
                              .ActionType(ActionType.CREATE)
                              .date(java.time.LocalDateTime.of(2023, 12, 20, 12, 0, 0))
                              .build())
                       .build();
    }

    @Test
    void SaveAndReadPage()
    {
        var save = repository.saveAndFlush(homepage);

        assertThat(save).isNotNull().isSameAs(homepage);
        assertThat(save.getId()).isNotNull();
    }

    @Test
    void FindPageByName()
    {
        repository.save(homepage);

        var find = repository.findByName("Home");

        assertThat(find).isPresent();
    }
}
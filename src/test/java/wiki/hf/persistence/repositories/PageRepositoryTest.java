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
class PageRepositoryTest
{
    @Autowired
    private PageRepository repository;

    Page page;

    @BeforeEach
    void SetupPage()
    {
        page = Page.builder()
                   .name("Weapons")
                   .description("The Weapons of the game.")
                   .action(Action.builder()
                                 .ActionType(ActionType.CREATE)
                                 .date(LocalDateTime.of(2023, 12, 20, 12, 0, 0))
                                 .build())
                   .build();

        repository.save(page);
    }

    @Test
    void ReadPage()
    {
        assertThat(repository.save(page)).isNotNull().isSameAs(page);
        assertThat(repository.save(page).getId()).isNotNull();
        assertThat(repository.save(page).getName()).isEqualTo("Weapons");
    }

    /*
    @Test
    void FindPageById()
    {
        assertThat(repository.findById()).isNotNull();
    }
    */

    // TODO: Fix this test.
    @Test
    void FindPageByName()
    {
        assertThat(repository.findByName("Weapons")).isPresent();
    }

    /*
    @Test
    void FindPageByAction()
    {

    }
    */
}
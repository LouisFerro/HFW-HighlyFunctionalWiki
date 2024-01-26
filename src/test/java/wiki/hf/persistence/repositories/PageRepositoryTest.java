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
    private @Autowired PageRepository repository;

    Action action;
    Page page;

    @BeforeEach
    void SetupPage() {
        action = TestFixtures.action(TestFixtures.account());
        page = Page.builder()
                   .name("Weapons")
                   .description("The Weapons of the game.")
                   .version("0.1.0")
                   .action(action)
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
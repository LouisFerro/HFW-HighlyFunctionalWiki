/*

package wiki.hf.persistence;

import org.junit.jupiter.api.BeforeEach;
import wiki.hf.domain.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
//@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.ANY)
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
                       .build();
    }

    @Test
    void SaveAndReadPage()
    {
        var save = repository.save(homepage);

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

*/
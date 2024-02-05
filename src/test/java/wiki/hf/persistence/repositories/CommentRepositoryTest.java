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
class CommentRepositoryTest {

    private @Autowired CommentRepository repository;

    Action action;
    Comment comment;

    @BeforeEach
    void SetUpSection() {
        action = TestFixtures.action(TestFixtures.account());
        comment = Comment.builder()
                .text("I like this page!")
                .edited(true)
                .page(Page.builder()
                        .name("Weapons")
                        .description("The Weapons of the game")
                        .action(action)
                        .build())
                .build();

        repository.save(comment);
    }

    @Test
    void ReadSection() {
        assertThat(repository.save(comment)).isNotNull().isSameAs(comment);
        assertThat(repository.save(comment).getText()).isEqualTo("I like this page!");
        assertThat(repository.save(comment).getId()).isNotNull();
    }

    /*
    @Test
    void FindSectionByAction()
    {

    }
    */
}
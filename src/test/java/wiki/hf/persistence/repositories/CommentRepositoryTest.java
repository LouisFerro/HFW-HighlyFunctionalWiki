package wiki.hf.persistence.repositories;

import wiki.hf.*;
import wiki.hf.domain.*;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import org.junit.jupiter.api.*;

@DataJpaTest
@Import(ApplicationTestContainerConfiguration.class)
class CommentRepositoryTest {

    private @Autowired CommentRepository repository;

    Action action;
    Comment comment;

    @BeforeEach
    void SetupComment() {
        action = TestFixtures.Action(TestFixtures.LF_Owner());
        comment = Comment.builder()
                .text("I like this page!")
                .action(action)
                .page(Item.builder()
                        .name("Weapons")
                        .description("The Weapons of the game")
                        .action(action)
                        .itemType(ItemType.PAGE)
                        .build())
                .build();

        repository.save(comment);
    }

    @Test
    void ReadComment() {
        assertThat(repository.save(comment)).isNotNull().isSameAs(comment);
        assertThat(repository.save(comment).getText()).isEqualTo("I like this page!");
        assertThat(repository.save(comment).getId()).isNotNull();
    }
}
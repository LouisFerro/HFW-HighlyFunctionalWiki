package wiki.hf.persistence.repositories;

import wiki.hf.*;
import wiki.hf.domain.*;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import org.junit.jupiter.api.*;

@DataJpaTest
@Import(TestContainerConfiguration.class)
class TokenRepositoryTest {

    @Autowired
    private TokenRepository repository;

    Token token;

    @BeforeEach
    void setup()
    {
        token = Token.builder().value("a8bN3").type(TokenType.CONFIRMATION).build();
    }

    @Test
    void saveAndReadToken()
    {
        assertThat(repository.save(token)).isNotNull();
        assertThat(repository.save(token).getId()).isNotNull();
    }
}
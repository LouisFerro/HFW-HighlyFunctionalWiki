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
class AccountRepositoryTest
{
    private @Autowired AccountRepository repository;
    Account account;

    @BeforeEach
    void setup() {
        account = TestFixtures.LF_Owner();
    }

    @Test
    void saveAndReadAccount() {
        assertThat(repository.save(account)).isNotNull().isSameAs(account);
        assertThat(repository.save(account).getId()).isNotNull();
    }
}
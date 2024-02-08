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
class AccountRepositoryTest
{
    @Autowired
    private AccountRepository repository;

    Account account;
    @BeforeEach
    void SetupAccount()
    {
        account = TestFixtures.account();
    }

    @Test
    void SaveAndReadAccount()
    {
        assertThat(repository.save(account)).isNotNull().isSameAs(account);
        assertThat(repository.save(account).getId()).isNotNull();
    }
}
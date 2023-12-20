package wiki.hf.persistence.repositories;

import wiki.hf.domain.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.ANY)
class AccountRepositoryTest
{
    @Autowired
    private AccountRepository repository;

    @Test
    void SavingAndReadingAnAccountTest()
    {
        var account = Account.builder()
                             .fullName("Louis Ferro")
                             .username("Louisthemagic")
                             .password("password")
                             .build();

        var save = repository.saveAndFlush(account);

        assertThat(save).isNotNull().isSameAs(account);
        assertThat(save.getId()).isNotNull();
    }
}
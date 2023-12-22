package wiki.hf.persistence.repositories;

import wiki.hf.domain.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@DataJpaTest
class AccountRepositoryTest
{
    @Autowired
    private AccountRepository repository;

    @Test
    void CreatingAnAccount()
    {
        var account = Account.builder()
                             .fullName("Louis Ferro")
                             .username("Louisthemagic")
                             .password("password")
                             .change(Change.builder()
                                           .changeType(ChangeType.CREATE)
                                           .date(LocalDateTime.of(2023, 12, 20, 12, 0, 0))
                                           .build())
                             .build();

        var save = repository.saveAndFlush(account);

        assertThat(save).isNotNull().isSameAs(account);
        assertThat(save.getId()).isNotNull();
    }
}
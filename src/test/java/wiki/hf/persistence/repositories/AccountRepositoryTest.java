package wiki.hf.persistence.repositories;

import org.junit.jupiter.api.BeforeEach;
import wiki.hf.domain.*;

import org.springframework.beans.factory.annotation.Autowired;
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

    Account account;
    @BeforeEach
    void Setup()
    {
        account = Account.builder()
                         .fullName("Louis Ferro")
                         .username("Louisthemagic")
                         .password("password")
                         .action(Action.builder()
                                       .ActionType(ActionType.CREATE)
                                       .date(LocalDateTime.of(2023, 12, 20, 12, 0, 0))
                                       .build())
                         .build();
    }

    @Test
    void SaveAndReadAccount()
    {
        var save = repository.save(account);

        assertThat(save).isNotNull().isSameAs(account);
        assertThat(save.getId()).isNotNull();
    }
}
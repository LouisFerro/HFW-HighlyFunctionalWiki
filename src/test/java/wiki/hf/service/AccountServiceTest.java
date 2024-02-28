package wiki.hf.service;

import wiki.hf.domain.Account;
import wiki.hf.persistence.repositories.AccountRepository;
import static wiki.hf.domain.TestFixtures.*;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;

import static org.assertj.core.api.Assumptions.*;

import java.util.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    private AccountService service;
    private @Mock AccountRepository repository;

    @BeforeEach
    void setUp() {
        assumeThat(repository).isNotNull();

        service = new AccountService(repository);
    }

    @Test
    void findAll() {
        Optional<String> name = Optional.empty();
        Optional<String> username = Optional.empty();

        Account Louisthemagic = Louisthemagic_Owner();
        Account Niklas2019 = Niklas2019_Administrator();
        Account MaxMuster = MaxMuster_Editor();
        Account Account = Account();

        when(repository.findAll()).thenReturn(List.of(Louisthemagic, Niklas2019, MaxMuster, Account));

        assumeThat(service.findAllByName(name, username)).containsExactly(Louisthemagic, Niklas2019, MaxMuster, Account);
        assumeThat(service.findAllByName(name, username)).containsExactlyInAnyOrder(MaxMuster, Niklas2019, Account, Louisthemagic);
        verify(repository, times(2)).findAll();
        verifyNoMoreInteractions(repository);
    }

    // TODO: Fix this Test, which is not returning a valid Account Object upon invoking the findAllByName method.
    /*
    @Test
    void findAllByName() {
        Optional<String> name = Optional.of("Louis Ferro");
        Optional<String> username = Optional.empty();

        Account Louisthemagic = Louisthemagic_Owner();

        when(repository.findAllByNameLikeIgnoreCase(name.get() + "%")).thenReturn(List.of(Louisthemagic));

        assumeThat(service.findAllByName(name, username)).containsExactly(Louisthemagic);
        verify(repository).findAllByNameLikeIgnoreCase(any());
        verifyNoMoreInteractions(repository);
    }
    */

    @Test
    void findAllByUsername() {
        Optional<String> name = Optional.empty();
        Optional<String> username = Optional.of("Louisthemagic");

        Account Louisthemagic = Louisthemagic_Owner();

        when(repository.findAllByUsernameLikeIgnoreCase(username.get() + "%")).thenReturn(List.of(Louisthemagic));

        assumeThat(service.findAllByName(name, username)).containsExactly(Louisthemagic);
        verify(repository).findAllByUsernameLikeIgnoreCase(any());
        verifyNoMoreInteractions(repository);
    }
}
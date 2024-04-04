package wiki.hf.service;

import wiki.hf.domain.*;
import static wiki.hf.domain.TestFixtures.*;
import wiki.hf.persistence.repositories.*;
import wiki.hf.service.exceptions.*;

import org.mockito.*;
import org.mockito.junit.jupiter.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import wiki.hf.service.policies.exceptions.PasswordPolicyViolationException;

import static org.assertj.core.api.Assumptions.*;

import java.util.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    private AccountService service;
    private @Mock AccountRepository repository;

    Account account1;
    Account account2;
    Account account3;
    Account account4;

    String correctPassword;
    String incorrectPassword;

    @BeforeEach
    void setUp() {
        assumeThat(repository).isNotNull();

        service = new AccountService(repository);

        account1 = Louisthemagic_Owner();
        account2 = Niklas2019_Administrator();
        account3 = MaxMuster_Editor();
        account4 = Account();

        correctPassword = "Password";
        incorrectPassword = "password";
    }

    @Test
    void findAll() {
        Optional<String> name = Optional.empty();
        Optional<String> username = Optional.empty();

        when(repository.findAll()).thenReturn(List.of(account1, account2, account3, account4));

        assumeThat(service.findAllByName(name, username)).containsExactly(account1, account2, account3, account4);
        assumeThat(service.findAllByName(name, username)).containsExactlyInAnyOrder(account3, account2, account4, account1);

        verify(repository, times(2)).findAll();
        verifyNoMoreInteractions(repository);
    }

    // TODO: Fix this Test, which is not returning a valid Account Object upon invoking the findAllByName method.
    /*
    @Test
    void findAllByName() {
        Optional<String> name = Optional.of("Louis Ferro");
        Optional<String> username = Optional.empty();

        Account account = Louisthemagic_Owner();

        when(repository.findAllByNameLikeIgnoreCase(name.get() + "%")).thenReturn(List.of(account));

        assumeThat(service.findAllByName(name, username)).containsExactly(account);

        verify(repository).findAllByNameLikeIgnoreCase(any());
        verifyNoMoreInteractions(repository);
    }
    */

    @Test
    void findAllByUsername() {
        Optional<String> name = Optional.empty();
        Optional<String> username = Optional.of("Louisthemagic");

        when(repository.findAllByUsernameLikeIgnoreCase(username.get() + "%")).thenReturn(List.of(account1));

        assumeThat(service.findAllByName(name, username)).containsExactly(account1);

        verify(repository).findAllByUsernameLikeIgnoreCase(any());
        verifyNoMoreInteractions(repository);
    }

    /*
    @Test
    void findByUsernameAndPassword() {
        String correctUsername = "louisthemagic";
        String incorrectUsername = "louisthemagi";

        when(repository.findByUsernameIgnoreCaseAndPassword(correctUsername, correctPassword)).thenReturn(Optional.of(account1));
        when(repository.findByUsernameIgnoreCaseAndPassword(incorrectUsername, correctPassword)).thenThrow(
            AccountNoContentException.UsernameAndPassword(incorrectUsername, correctPassword)
        );
        when(repository.findByUsernameIgnoreCaseAndPassword(correctUsername, incorrectPassword)).thenThrow(
            AccountNoContentException.UsernameAndPassword(correctUsername, incorrectPassword)
        );

        assumeThat(service.findByUsernameAndPassword(correctUsername, correctPassword)).isSameAs(account1);
        assumeThat(service.findByUsernameAndPassword(incorrectUsername, correctPassword)).(AccountNoContentException.class);
        assumeThat(service.findByUsernameAndPassword(correctUsername, incorrectPassword)).isInstanceOf(AccountNoContentException.class);

        verify(repository, times(3)).findByUsernameIgnoreCaseAndPassword(any(), any());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void checkByIdAndPassword() {
        Long correctId = 1L;
        Long incorrectId = 2L;

        when(repository.existsByIdAndPassword(correctId, correctPassword)).thenReturn(true);
        when(repository.existsByIdAndPassword(incorrectId, correctPassword)).thenThrow(
                AccountNoContentException.IdAndPassword(incorrectId, correctPassword)
        );
        when(repository.existsByIdAndPassword(correctId, incorrectPassword)).thenThrow(
                AccountNoContentException.IdAndPassword(correctId, incorrectPassword)
        );

        verify(repository, times(3)).existsByIdAndPassword(any(), any());
        verifyNoMoreInteractions(repository);
    }
    */

    @Test
    void update() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }
}
package wiki.hf.service;

import wiki.hf.domain.*;

import static wiki.hf.domain.TestFixtures.*;
import wiki.hf.persistence.repositories.*;
import wiki.hf.presentation.dataTransferObjects.AccountRequest;
import wiki.hf.service.exceptions.*;

import org.mockito.*;
import org.mockito.junit.jupiter.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assumptions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    private AccountService service;
    private @Mock AccountRepository repository;

    Account account1;
    Account account2;
    Account account3;
    Account account4;

    String correctUsername;
    String incorrectUsername;
    String correctPassword;
    String incorrectPassword;

    Long correctId;
    Long incorrectId;

    @BeforeEach
    void setup() {
        assumeThat(repository).isNotNull();

        service = new AccountService(repository);

        account1 = LF_Owner();
        account2 = UK_Administrator();
        account3 = AS_Editor();
        account4 = NH_Reader();

        correctUsername = "Louisthemagic";
        incorrectUsername = "Louisthemagi";
        correctPassword = "Password";
        incorrectPassword = "password";

        correctId = 1L;
        incorrectId = 2L;
    }

    @Test
    void findAll() {
        Optional<String> name = Optional.empty();
        Optional<String> username = Optional.empty();

        when(repository.findAll()).thenReturn(List.of(account1, account2, account3, account4));

        assertThat(service.findAllByName(name, username)).containsExactly(account1, account2, account3, account4);
        assertThat(service.findAllByName(name, username)).containsExactlyInAnyOrder(account3, account2, account4, account1);

        verify(repository, times(2)).findAll();
        verifyNoMoreInteractions(repository);
    }

    // TODO: Fix this Test, which is not returning a valid Account Object upon invoking the findAllByName method.

    @Test
    void findAllByName() {
        Optional<String> name = Optional.of("Louis Ferro");
        Optional<String> username = Optional.empty();

        Account account = LF_Owner();

        when(repository.findAllByNameLikeIgnoreCase(name.get() + "%")).thenReturn(List.of(account));

        assertThat(service.findAllByName(name, username)).containsExactly(account);

        verify(repository).findAllByNameLikeIgnoreCase(any());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findAllByUsername() {
        Optional<String> name = Optional.empty();
        Optional<String> username = Optional.of("Louisthemagic");

        when(repository.findAllByUsernameLikeIgnoreCase(username.get() + "%")).thenReturn(List.of(account1));

        assumeThat(service.findAllByName(name, username)).containsExactly(account1);

        verify(repository).findAllByUsernameLikeIgnoreCase(any());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void checkByUsernameAndPassword() {
        when(repository.findByUsernameIgnoreCase(correctUsername)).thenReturn(Optional.of(account1));
        when(repository.findByUsernameIgnoreCase(incorrectUsername)).thenThrow(
            AccountException.NoAccountExists(incorrectUsername)
        );

        when(repository.findByUsernameIgnoreCaseAndPassword(correctUsername, correctPassword)).thenReturn(Optional.of(account1));
        when(repository.findByUsernameIgnoreCaseAndPassword(correctUsername, incorrectPassword)).thenThrow(
            AccountException.WrongPassword(correctUsername, incorrectPassword)
        );

        assertThat(service.findByUsernameAndPassword(correctUsername, correctPassword)).isSameAs(account1);
        assertThrows(AccountException.class, () -> service.findByUsernameAndPassword(incorrectUsername, correctPassword));
        assertThrows(AccountException.class, () -> service.findByUsernameAndPassword(correctUsername, incorrectPassword));

        verify(repository, times(2)).findByUsernameIgnoreCaseAndPassword(any(), any());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void checkByIdAndPassword() {
        when(repository.existsById(correctId)).thenReturn(true);
        when(repository.existsById(incorrectId)).thenThrow(AccountException.NoAccountExists(incorrectId));

        when(repository.existsByIdAndPassword(correctId, correctPassword)).thenReturn(true);
        when(repository.existsByIdAndPassword(correctId, incorrectPassword)).thenThrow(
            AccountException.WrongPassword(correctId, incorrectPassword)
        );

        assertThat(service.checkByIdAndPassword(correctId, correctPassword)).isTrue();
        assertThrows(AccountException.class, () -> service.checkByIdAndPassword(incorrectId, correctPassword));
        assertThrows(AccountException.class, () -> service.checkByIdAndPassword(correctId, incorrectPassword));

        verify(repository, times(2)).existsByIdAndPassword(any(), any());
        verifyNoMoreInteractions(repository);
    }

    // TODO: Fix

    @Test
    void createAccount() {
        Account invalidName             = new Account("", "NH", "NiklasHasenbacher123456789!", AccountType.READER);
        Account invalidUsername         = new Account("Niklas Hasenbacher", "", "NiklasHasenbacher123456789!", AccountType.READER);
        Account invalidAccountType      = new Account("Louis Ferro", "NH", "NiklasHasenbacher123456789!", null);
        Account invalidRepeatedPassword = new Account("Louis Ferro", "NH", "NiklasHasenbacher123456789!", AccountType.READER);

        Account invalidPasswordMissingCharacters       = new Account("Louis Ferro", "Louisthemagic", "aaa", AccountType.OWNER);
        Account invalidPasswordMissingDigit            = new Account("Louis Ferro", "Louisthemagic", "aaaaaaaaaaaa", AccountType.OWNER);
        Account invalidPasswordMissingLowercaseLetter  = new Account("Louis Ferro", "Louisthemagic", "AAAAAA123456", AccountType.OWNER);
        Account invalidPasswordMissingUppercaseLetter  = new Account("Louis Ferro", "Louisthemagic", "aaaaaa123456", AccountType.OWNER);
        Account invalidPasswordMissingSpecialCharacter = new Account("Louis Ferro", "Louisthemagic", "aaaAAA123456", AccountType.OWNER);

        when(repository.save(account4)).thenReturn(account4);

        when(repository.save(invalidName)).thenThrow(AccountException.NoName());
        when(repository.save(invalidUsername)).thenThrow(AccountException.NoUsername());
        when(repository.save(invalidAccountType)).thenReturn(account4);
        when(repository.save(invalidRepeatedPassword)).thenThrow(AccountException.WrongRepeatedPassword());

        when(repository.save(invalidPasswordMissingCharacters)).thenThrow(PasswordPolicyException.MissingCharacters());
        when(repository.save(invalidPasswordMissingDigit)).thenThrow(PasswordPolicyException.MissingDigit());
        when(repository.save(invalidPasswordMissingLowercaseLetter)).thenThrow(PasswordPolicyException.MissingLowercaseLetter());
        when(repository.save(invalidPasswordMissingUppercaseLetter)).thenThrow(PasswordPolicyException.MissingUppercaseLetter());
        when(repository.save(invalidPasswordMissingSpecialCharacter)).thenThrow(PasswordPolicyException.InvalidOrMissingSpecialCharacter());

        assertThat(service.create(AccountRequest.New(account4))).isSameAs(account4);

        assertThrows(AccountException.class, () -> service.create(AccountRequest.New(invalidName)));
        assertThrows(AccountException.class, () -> service.create(AccountRequest.New(invalidUsername)));
        assertThat(service.create(AccountRequest.New(invalidAccountType))).isEqualTo(account4);
        assertThrows(AccountException.class, () -> service.create(AccountRequest.New(invalidRepeatedPassword)));

        assertThrows(PasswordPolicyException.class, () -> service.create(AccountRequest.New(invalidPasswordMissingCharacters)));
        assertThrows(PasswordPolicyException.class, () -> service.create(AccountRequest.New(invalidPasswordMissingDigit)));
        assertThrows(PasswordPolicyException.class, () -> service.create(AccountRequest.New(invalidPasswordMissingLowercaseLetter)));
        assertThrows(PasswordPolicyException.class, () -> service.create(AccountRequest.New(invalidPasswordMissingUppercaseLetter)));
        assertThrows(PasswordPolicyException.class, () -> service.create(AccountRequest.New(invalidPasswordMissingSpecialCharacter)));

        verify(repository, times(9)).save(any());
        verifyNoMoreInteractions(repository);
    }

    // TODO: Fix
    @Test
    void deleteAccount() {
        repository.save(account1);

        when(repository.save(account1)).thenReturn(account1);
        when(repository.existsById(Objects.requireNonNull(repository.save(account1).getId()))).thenReturn(true);

        service.delete(account1.getId());

        verify(repository).deleteById(account1.getId());
    }
}
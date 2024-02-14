package wiki.hf.domain;

import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;

public class TestFixtures {

    public static Account Louisthemagic_Owner()
    {
        return account("Louis Ferro", "Louisthemagic", "Password", AccountType.OWNER);
    }

    public static Account Niklas2019_Administrator()
    {
        return account("Niklas Hasenbacher", "Niklas2019", "Password", AccountType.ADMINISTRATOR);
    }

    public static Account MaxMuster_Editor()
    {
        return account("Maximilian Mustermann", "MaxMuster", "Password", AccountType.EDITOR);
    }

    public static Account account()
    {
          return account("FullName", "Username", "Password", AccountType.USER);
    }

    public static Account account(String fullName, String username, String password, AccountType accountType)
    {
        return Account.builder()
                      .fullName(fullName)
                      .username(username)
                      .password(password)
                      .accountType(accountType)
                      .build();
    }

    public static Action Year2023December20Hour12_Creation()
    {
        return action(account(), LocalDateTime.of(2023, 12, 20, 12, 0, 0), ActionType.CREATE);
    }

    public static Action Year2023December20Hour12_Update()
    {
        return action(account(), LocalDateTime.of(2023, 12, 20, 12, 0, 0), ActionType.CREATE);
    }

    public static Action Year2023December20Hour12_Deletion()
    {
        return action(account(), LocalDateTime.of(2023, 12, 20, 12, 0, 0), ActionType.CREATE);
    }

    public static Action action()
    {
        return action(account(), LocalDateTime.of(1, 1, 1, 0, 0, 0), ActionType.CREATE);
    }

    public static Action action(Account account)
    {
        return action(account, LocalDateTime.of(1, 1, 1, 0, 0, 0), ActionType.CREATE);
    }

    public static Action action(Account account, LocalDateTime alteration, ActionType actionType)
    {
        return Action.builder()
                .account(account)
                .alteration(alteration)
                .actionType(actionType)
                .build();
    }
}
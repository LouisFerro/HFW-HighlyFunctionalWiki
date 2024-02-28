package wiki.hf.domain;

import java.time.LocalDateTime;

public class TestFixtures {

    public static Account Louisthemagic_Owner()
    {
        return Account("Louis Ferro", "Louisthemagic", "Password", AccountType.OWNER);
    }

    public static Account Niklas2019_Administrator()
    {
        return Account("Niklas Hasenbacher", "Niklas2019", "Password", AccountType.ADMINISTRATOR);
    }

    public static Account MaxMuster_Editor()
    {
        return Account("Maximilian Mustermann", "MaxMuster", "Password", AccountType.EDITOR);
    }

    public static Account Account()
    {
          return Account("Name", "Username", "Password", AccountType.USER);
    }

    public static Account Account(String name, String username, String password, AccountType accountType)
    {
        return Account.builder()
                      .name(name)
                      .username(username)
                      .password(password)
                      .accountType(accountType)
                      .build();
    }

    public static Action Year2023December20Hour12_Creation()
    {
        return Action(Account(), LocalDateTime.of(2023, 12, 20, 12, 0, 0), ActionType.CREATE);
    }

    public static Action Year2023December20Hour12_Update()
    {
        return Action(Account(), LocalDateTime.of(2023, 12, 20, 12, 0, 0), ActionType.CREATE);
    }

    public static Action Year2023December20Hour12_Deletion()
    {
        return Action(Account(), LocalDateTime.of(2023, 12, 20, 12, 0, 0), ActionType.CREATE);
    }

    public static Action Action()
    {
        return Action(Account(), LocalDateTime.of(1, 1, 1, 0, 0, 0), ActionType.CREATE);
    }

    public static Action Action(Account account)
    {
        return Action(account, LocalDateTime.of(1, 1, 1, 0, 0, 0), ActionType.CREATE);
    }

    public static Action Action(Account account, LocalDateTime alteration, ActionType actionType)
    {
        return Action.builder()
                .account(account)
                .alteration(alteration)
                .actionType(actionType)
                .build();
    }
}
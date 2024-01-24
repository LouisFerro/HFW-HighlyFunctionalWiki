package wiki.hf.domain;

import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;

public class TestFixtures {

     public static Account account()
     {
          return account("Louis Ferro", "Louisthemagic", "password", AccountType.OWNER);
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

    public static Action action()
    {
        return action(account(), LocalDateTime.of(2023, 12, 20, 12, 0, 0), LocalDateTime.of(2023, 12, 9, 12, 0, 0), ActionType.CREATE);
    }

    public static Action action(Account account)
    {
        return action(account, LocalDateTime.of(2023, 12, 20, 12, 0, 0), LocalDateTime.of(2023, 12, 9, 12, 0, 0), ActionType.CREATE);
    }

    public static Action action(Account account, LocalDateTime alteration, LocalDateTime deletion, ActionType actionType)
    {
        return Action.builder()
                .account(account)
                .alteration(alteration)
                .deletion(deletion)
                .actionType(actionType)
                .build();
    }
}
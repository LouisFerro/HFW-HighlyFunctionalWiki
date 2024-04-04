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
          return Account("Name", "Username", "Password", AccountType.READER);
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
        return Action(Account(), LocalDateTime.of(2023, 12, 20, 12, 0, 0), ActionType.CREATION);
    }

    public static Action Year2023December20Hour12_Update()
    {
        return Action(Account(), LocalDateTime.of(2023, 12, 20, 12, 0, 0), ActionType.CREATION);
    }

    public static Action Year2023December20Hour12_Deletion()
    {
        return Action(Account(), LocalDateTime.of(2023, 12, 20, 12, 0, 0), ActionType.CREATION);
    }

    public static Action Action()
    {
        return Action(Account(), LocalDateTime.of(1, 1, 1, 0, 0, 0), ActionType.CREATION);
    }

    public static Action Action(Account account)
    {
        return Action(account, LocalDateTime.of(1, 1, 1, 0, 0, 0), ActionType.CREATION);
    }

    public static Action Action(Account account, LocalDateTime alteration, ActionType actionType)
    {
        return Action.builder()
                .account(account)
                .alteration(alteration)
                .actionType(actionType)
                .build();
    }

    public static Item FourMountainWalk_Page()
    {
        return item(null, null, "4 Mountain Walk", "What is the 4 Mountain Walk", Content.builder().text(null).build(), ItemType.PAGE,
                    Action(Louisthemagic_Owner(), LocalDateTime.of(2023, 3, 5, 20, 30), ActionType.CREATION));
    }

    public static Item Introduction_Section()
    {
        return item(FourMountainWalk_Page(), FourMountainWalk_Page(), "Introduction", "Introductory text and table of contents", Content.builder().text(null).build(), ItemType.SECTION,
                Action(Louisthemagic_Owner(), LocalDateTime.of(2023, 3, 5, 20, 30), ActionType.CREATION));
    }

    public static Item Generics_Text()
    {
        return item(FourMountainWalk_Page(), Introduction_Section(), "Introduction", null,
                Content.builder().text("The 4 Mountain walk is a annually event that takes places in Carinthia, Austria between 12 o clock am and 16 o clock pm. " +
                                       "It was first being held in the years around 1500 and since then it has been the tradition of the local people to walk the 55km on the second friday after Easter. " +
                                       "The entire path leads over the 4 mountains of the \"Magdalensberg\", \"Ulrichsberg\", \"Veitsberg\" and \"Lorenziberg\" which is close to the state capital of Klagenfurt.").build(),
                ItemType.TEXT, Action(Louisthemagic_Owner(), LocalDateTime.of(2023, 3, 5, 20, 30), ActionType.CREATION));
    }

    public static Item TableOfContents_List()
    {
        return item(FourMountainWalk_Page(), Introduction_Section(), "TableOfContents", "A list of all the sections and items", Content.builder().text(null).build(), ItemType.LIST,
                Action(Louisthemagic_Owner(), LocalDateTime.of(2023, 3, 6, 8, 15), ActionType.CREATION));
    }

    public static Item Introduction_List()
    {
        return item(FourMountainWalk_Page(), TableOfContents_List(), "Introduction", null, Content.builder().text("1. Introduction").build(), ItemType.LIST,
                Action(Louisthemagic_Owner(), LocalDateTime.of(2023, 3, 5, 8, 45), ActionType.CREATION));
    }

    public static Item Generics_Section()
    {
        return item(FourMountainWalk_Page(), Introduction_List(), "Generics", null, Content.builder().text("1.1. Generics").build(), ItemType.SECTION,
                Action(Louisthemagic_Owner(), LocalDateTime.of(2023, 3, 5, 8, 45), ActionType.CREATION));
    }

    public static Item TableOfContents_Section()
    {
        return item(FourMountainWalk_Page(), Introduction_List(), "TableOfContents", null, Content.builder().text("1.2. Table of Contents").build(), ItemType.SECTION,
                Action(Louisthemagic_Owner(), LocalDateTime.of(2023, 3, 5, 8, 45), ActionType.CREATION));
    }


    public static Item item(Item page, Item parent, String name, String description, Content content, ItemType itemType, Action action)
    {
        return Item.builder()
                   .page(page)
                   .parent(parent)
                   .name(name)
                   .description(description)
                   .content(content)
                   .itemType(itemType)
                   .action(action)
                   .build();
    }
}
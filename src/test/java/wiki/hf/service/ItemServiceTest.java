package wiki.hf.service;

import wiki.hf.domain.*;
import wiki.hf.persistence.repositories.*;
import static wiki.hf.domain.TestFixtures.*;

import org.mockito.*;
import org.mockito.junit.jupiter.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;

import static org.assertj.core.api.Assumptions.*;

import java.util.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    private ItemService service;
    private @Mock ItemRepository repository;

    @BeforeEach
    void setUp() {
        assumeThat(repository).isNotNull();

        service = new ItemService(repository);
    }

    @Test
    void findPageByName() {
        String name = "4 Mountain Walk";
        Item page = FourMountainWalk_Page();

        when(repository.findByNameAndItemType(name, ItemType.PAGE)).thenReturn(Optional.of(page));

        assumeThat(service.findPageByName(name)).containsSame(page);

        verify(repository).findByNameAndItemType(name, ItemType.PAGE);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findItemsByPage() {
        Item page = FourMountainWalk_Page();
        List<Item> items = List.of(FourMountainWalk_Page(), Introduction_Section(), Generics_Text(),
                                   TableOfContents_List(), Introduction_List(), Generics_Section(), TableOfContents_Section());

        when(repository.findAllByPage(page)).thenReturn(items);

        assumeThat(service.findItemsByPage(page)).containsExactlyElementsOf(items);

        verify(repository).findAllByPage(page);
        verifyNoMoreInteractions(repository);
    }
}
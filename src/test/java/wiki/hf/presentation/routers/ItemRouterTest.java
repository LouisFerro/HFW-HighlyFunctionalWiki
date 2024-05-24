package wiki.hf.presentation.routers;

import wiki.hf.domain.*;
import wiki.hf.service.*;
import static wiki.hf.domain.TestFixtures.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;

import java.util.*;

@WebMvcTest(ItemRouter.class)
class ItemRouterTest {

    private @Autowired MockMvc mockMvc;
    private @Autowired ObjectMapper objectMapper;
    private @MockBean ItemService itemService;

    @Test
    void browsePage() throws Exception {
        Item page = FourMountainWalk_Page();
        List<Item> items = List.of(FourMountainWalk_Page(), Introduction_Section(), Generics_Text(),
                                   TableOfContents_List(), Introduction_List(), Generics_Section(), TableOfContents_Section());

        when(itemService.findItemsByPage(page)).thenReturn(items);
        when(itemService.findPageByName("4 Mountain Walk")).thenReturn(Optional.of(page));

        mockMvc.perform(get("/page/4 Mountain Walk"))
               .andExpect(status().isOk())
               .andExpect(model().attribute("page", page))
               .andExpect(model().attribute("items", items))
               .andExpect(view().name("page/index"))
               .andDo(print());
    }
}
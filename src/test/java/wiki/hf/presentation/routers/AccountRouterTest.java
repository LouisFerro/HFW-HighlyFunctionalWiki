package wiki.hf.presentation.routers;

import wiki.hf.domain.*;
import static wiki.hf.domain.TestFixtures.*;
import wiki.hf.service.*;
import wiki.hf.presentation.dataTransferObjects.*;

import org.springframework.security.test.context.support.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;

import java.util.*;

@WebMvcTest(AccountRouter.class)
class AccountRouterTest {

    private @Autowired MockMvc mvc;
    private @Autowired ObjectMapper mapper;
    private @MockBean AccountService service;

    @Test
    @WithMockUser(username = "username", roles = "ADMINISTRATOR")
    void login() throws Exception {
        mvc.perform(get("/account/login"))
           .andExpect(status().isOk())
           .andExpect(view().name("account/login"))
           .andDo(print());
    }

    @Test
    @WithMockUser(username = "username", roles = "ADMINISTRATOR")
    void register() throws Exception {
        mvc.perform(get("/account/register"))
           .andExpect(status().isOk())
           .andExpect(model().attribute("accountRequest", AccountRequest.Base()))
           .andExpect(view().name("account/register"))
           .andDo(print());
    }

    @Test
    @WithMockUser(username = "username", roles = "ADMINISTRATOR")
    void create() throws Exception {
        AccountRequest accountRequest = AccountRequest.New(LF_Owner());

        when(service.create(accountRequest)).thenReturn(LF_Owner());

        mvc.perform(post("/account/register").with(csrf()))
           .andExpect(status().isCreated())
           .andExpect(redirectedUrl("/account/management"))
           .andExpect(view().name("account/management"))
           .andDo(print());
    }

    @Test
    @WithMockUser(username = "username", roles = "ADMINISTRATOR")
    void manage() throws Exception {
        List<Account> accounts = List.of(LF_Owner(), UK_Administrator(), AS_Editor(), NH_Reader());

        when(service.findAllByName(Optional.empty(), Optional.empty())).thenReturn(accounts);

        mvc.perform(get("/account/management"))
           .andExpect(status().isOk())
           .andExpect(model().attribute("accounts", accounts))
           .andExpect(model().attribute("accountRequest", AccountRequest.Base()))
           .andExpect(view().name("account/management"))
           .andDo(print());
    }

    @Test
    void update() throws Exception {
        AccountRequest accountRequest = AccountRequest.New(LF_Owner());

        when(service.update(accountRequest)).thenReturn(LF_Owner());

        mvc.perform(put("/account/management").with(csrf()))
            .andExpect(status().isCreated())
            .andExpect(redirectedUrl("/account/management"))
            .andExpect(view().name("account/management"))
            .andDo(print());
    }

    // TODO: Implement

    @Test
    void delete() throws Exception {

    }
}
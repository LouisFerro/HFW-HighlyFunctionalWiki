package wiki.hf.presentation.controllers;

import wiki.hf.domain.*;
import static wiki.hf.domain.TestFixtures.*;
import wiki.hf.presentation.controllers.*;
import wiki.hf.presentation.dataTransferObjects.*;
import wiki.hf.service.AccountService;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.security.test.context.support.*;

import static org.assertj.core.api.Assumptions.assumeThat;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import java.util.*;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    private @Autowired MockMvc mvc;
    private @Autowired ObjectMapper mapper;
    private @MockBean AccountService service;

    @BeforeEach
    void setup() {
        assumeThat(mvc).isNotNull();
        assumeThat(service).isNotNull();
    }

    @Test
    @WithMockUser(username = "username", roles = "ADMINISTRATOR")
    void getAccountsWithContent() throws Exception {
        when(service.findAllByName(Optional.empty(), Optional.empty()))
                    .thenReturn(List.of(LF_Owner(), UK_Administrator(), AS_Editor(), NH_Reader()));

        mvc.perform(get(AccountController.url).accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$[0].username").value("LF"))
           .andExpect(jsonPath("$[1].username").value("UK"))
           .andExpect(jsonPath("$[2].username").value("AS"))
           .andExpect(jsonPath("$[3].username").value("NH"))
           .andDo(print());
    }

    @Test
    @WithMockUser(username = "username", roles = "ADMINISTRATOR")
    void getAccountsWithoutContent() throws Exception {
        when(service.findAllByName(any(), any())).thenReturn(Collections.emptyList());

        mvc.perform(get(AccountController.url).accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isNoContent())
           .andDo(print());
    }

    // TODO: Implement

    @Test
    void updateAccount() throws Exception {

    }

    @Test
    @WithMockUser(username = "username", roles = "ADMINISTRATOR")
    void saveAccount() throws Exception {
        Account account = spy(TestFixtures.LF_Owner());
        AccountRequest accountRequest = AccountRequest.New(account);

        when(account.getId()).thenReturn(1L);
        when(service.create(accountRequest)).thenReturn(account);

        var request = post(AccountController.url + "/register").accept(MediaType.APPLICATION_JSON)
                                                                          .contentType(MediaType.APPLICATION_JSON)
                                                                          .content(mapper.writeValueAsString(accountRequest))
                                                                          .with(csrf());

        mvc.perform(request)
           .andExpect(status().isCreated())
           .andExpect(header().string(HttpHeaders.LOCATION, "/api/account/LF"))
           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$.username").value(account.getUsername()))
           .andDo(print());
    }

    // TODO: Implement
    @Test
    void deleteAccount() throws Exception {

    }
}
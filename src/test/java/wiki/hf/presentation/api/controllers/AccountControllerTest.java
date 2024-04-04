package wiki.hf.presentation.api.controllers;

import wiki.hf.domain.Account;
import wiki.hf.domain.TestFixtures;
import wiki.hf.presentation.api.commands.AccountCommand;
import wiki.hf.service.AccountService;

import static wiki.hf.domain.TestFixtures.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    void getAccountsWithContent() throws Exception {
        when(service.findAllByName(Optional.empty(), Optional.empty()))
                    .thenReturn(List.of(Louisthemagic_Owner(), Niklas2019_Administrator(), MaxMuster_Editor(), Account()));

        mvc.perform(get(AccountController.url).accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$[0].username").value("Louisthemagic"))
           .andExpect(jsonPath("$[1].username").value("Niklas2019"))
           .andExpect(jsonPath("$[2].username").value("MaxMuster"))
           .andExpect(jsonPath("$[3].username").value("Username"))
           .andDo(print());
    }

    @Test
    void getAccountsWithoutContent() throws Exception {
        when(service.findAllByName(any(), any())).thenReturn(Collections.emptyList());

        mvc.perform(get(AccountController.url).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void saveAccount() throws Exception {
        Account account = spy(TestFixtures.Louisthemagic_Owner());

        when(account.getId()).thenReturn(1L);
        when(service.save(any(), any(), any())).thenReturn(account);

        AccountCommand command = new AccountCommand(account.getName(), account.getUsername(), account.getPassword());
        var request = post(AccountController.url + "/register").accept(MediaType.APPLICATION_JSON)
                                                                          .contentType(MediaType.APPLICATION_JSON)
                                                                          .content(mapper.writeValueAsString(command));

        mvc.perform(request)
           .andExpect(status().isCreated())
           .andExpect(header().string(HttpHeaders.LOCATION, "/api/account/1"))
           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$.username").value(account.getUsername()))
           .andDo(print());
    }
}
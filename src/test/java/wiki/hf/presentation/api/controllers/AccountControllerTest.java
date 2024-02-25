package wiki.hf.presentation.api.controllers;

import wiki.hf.service.AccountService;
import static wiki.hf.domain.TestFixtures.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.assertj.core.api.Assumptions.assumeThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.*;
import java.util.*;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    private @Autowired MockMvc mvc;
    private @MockBean AccountService service;

    @BeforeEach
    void setup() {
        assumeThat(mvc).isNotNull();
        assumeThat(service).isNotNull();
    }

    @Test
    void getAccountsWithContent() throws Exception {
        when(service.findAllByName(Optional.empty(), Optional.empty()))
                    .thenReturn(List.of(Louisthemagic_Owner(), Niklas2019_Administrator(), MaxMuster_Editor(), account()));

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
}
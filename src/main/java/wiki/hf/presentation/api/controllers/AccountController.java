package wiki.hf.presentation.api.controllers;

import static wiki.hf.presentation.api.Base.*;
import wiki.hf.presentation.api.dataTransferObjects.AccountDTO;
import wiki.hf.service.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import java.util.*;

@RequiredArgsConstructor

@RestController
@RequestMapping(account)
public class AccountController {

    private final AccountService service;
    public static final String url = account;

    @GetMapping
    public HttpEntity<List<AccountDTO>> getAccounts(@RequestParam Optional<String> name, @RequestParam Optional<String> username) {
        List<AccountDTO> accountDTO = service.findAllByName(name, username)
                                             .stream()
                                             .map(AccountDTO::new)
                                             .toList();

        return accountDTO.isEmpty() ? ResponseEntity.noContent().build()
                                    : ResponseEntity.ok(accountDTO);
    }

    @GetMapping("/{username}/{password}")
    public HttpEntity<AccountDTO> getAccount(@PathVariable String username, @PathVariable String password) {
        return ResponseEntity.ok(new AccountDTO(service.findByUsernameAndPassword(username, password)));
    }
}

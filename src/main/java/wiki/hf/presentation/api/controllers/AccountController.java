package wiki.hf.presentation.api.controllers;

import wiki.hf.presentation.api.dataTransferObjects.AccountDTO;
import wiki.hf.service.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import java.util.*;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService service;

    @GetMapping
    public HttpEntity<List<AccountDTO>> getAccounts(@RequestParam Optional<String> name, @RequestParam Optional<String> username) {
        List<AccountDTO> accountDTO = service.findAllByName(name, username)
                .stream()
                .map(AccountDTO::new)
                .toList();

        return accountDTO.isEmpty() ? ResponseEntity.noContent().build()
                                    : ResponseEntity.ok(accountDTO);
    }
}

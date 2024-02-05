package wiki.hf.presentation.api.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import wiki.hf.presentation.api.dataTransferObjects.AccountDTO;
import wiki.hf.service.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService service;

    @GetMapping
    public HttpEntity<List<AccountDTO>> getAccounts(@RequestParam Optional<String> fullName, @RequestParam Optional<String> username) {
        List<AccountDTO> accountDTO = service.findByName(fullName.toString(), username.toString())
                                             .stream()
                                             .map(AccountDTO::new)
                                             .toList();

        return accountDTO.isEmpty() ? ResponseEntity.noContent().build()
                                    : ResponseEntity.ok(accountDTO);
    }
}

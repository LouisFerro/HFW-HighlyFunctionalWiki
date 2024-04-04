package wiki.hf.presentation.api.controllers;

import static wiki.hf.presentation.api.Base.*;

import wiki.hf.domain.*;
import wiki.hf.presentation.api.dataTransferObjects.*;
import wiki.hf.presentation.api.commands.*;
import wiki.hf.service.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import lombok.*;
import lombok.extern.log4j.*;
import jakarta.validation.*;

import java.net.*;
import java.util.*;

@RequiredArgsConstructor
@Log4j2

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

    @PostMapping("/register")
    public HttpEntity<AccountDTO> postAccount(@RequestBody @Valid AccountCommand command) {
        Account account = service.save(command.name(), command.username(), command.password());
        URI location = URI.create("%s/%d".formatted(url, account.getId()));

        return ResponseEntity.created(location).body(new AccountDTO(account));
    }
}

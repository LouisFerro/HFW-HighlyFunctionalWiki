package wiki.hf.presentation.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import wiki.hf.presentation.api.dto.AccountDTO;
import wiki.hf.service.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService service;

    @GetMapping
    public HttpEntity<List<AccountDTO>> getAccounts() {
        List<AccountDTO> accountDTO = service.findAll()
                                             .stream()
                                             .map(AccountDTO::new)
                                             .toList();

        return accountDTO.isEmpty() ? ResponseEntity.noContent().build()
                                    : ResponseEntity.ok(accountDTO);
    }
}

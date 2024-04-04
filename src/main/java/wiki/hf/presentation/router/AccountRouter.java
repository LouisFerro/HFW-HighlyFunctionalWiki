package wiki.hf.presentation.router;

import wiki.hf.domain.*;
import wiki.hf.service.*;
import wiki.hf.presentation.forms.*;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.*;
import lombok.extern.log4j.*;

import jakarta.validation.*;
import wiki.hf.service.policies.exceptions.PasswordPolicyViolationException;
import wiki.hf.service.exceptions.UserAlreadyExistsException;

import java.util.*;

@RequiredArgsConstructor

@Log4j2
@Controller
@RequestMapping("/account")
public class AccountRouter {

    private final AccountService service;

    @GetMapping("/login")
    private String loginAccount() { return "account/login"; }

    @GetMapping("/register")
    private String register(Model model) {
        log.debug("Rendering account page: register.html");

        model.addAttribute("accountForm", AccountForm.base());

        return "account/register";
    }

    @PostMapping("/register")
    private String postAccount(Model model, @Valid @ModelAttribute("accountForm") AccountForm accountForm) {
        log.info("Saving account: {}", accountForm);

        try {
            service.save(accountForm.name(), accountForm.username(), accountForm.password());

            return "redirect:/management";
        }
        catch (UserAlreadyExistsException exception) {
            log.error(exception.getMessage());

            model.addAttribute("exception", "UserAlreadyExists");
        }
        catch (PasswordPolicyViolationException exception) {
            log.error(exception.getMessage());

            model.addAttribute("exception", exception.type);
        }

        return "account/register";
    }

    @GetMapping("/management")
    private String manageAccounts(Model model) {
        log.debug("Loading accounts");

        List<Account> accounts = service.findAllByName(Optional.empty(), Optional.empty());

        log.debug("Rendering account page: management.html");

        model.addAttribute("accounts", accounts);

        return "account/management";
    }

    @PutMapping("/update")
    private void updateAccount(@RequestBody Account account) {
        log.info("Updating account: {}", account);

        service.update(account);
    }

    @PostMapping("/post")
    private String postAccount(@RequestBody Account account) {
        // service.save(account);

        return "account/register";
    }

    @DeleteMapping("/delete/{id}")
    private void deleteAccount(Model model, @PathVariable Long id, @RequestBody String password) {
        log.info("Checking account by id {} and password {}", id, password);

        try {
            service.checkByIdAndPassword(id, password);

            log.info("Deleting account with id: {} and password {}", id, password);

            service.delete(id);
        }
        catch (Exception exception) {
            model.addAttribute("error", "error");

            log.error(exception);
        }
    }
}

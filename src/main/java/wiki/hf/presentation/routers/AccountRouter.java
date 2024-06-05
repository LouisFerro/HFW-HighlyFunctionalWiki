package wiki.hf.presentation.routers;

import wiki.hf.domain.*;
import wiki.hf.presentation.dataTransferObjects.AccountRequest;
import wiki.hf.service.*;
import wiki.hf.service.exceptions.*;

import org.springframework.validation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.*;
import lombok.extern.log4j.*;

import java.util.*;

@RequiredArgsConstructor

@Log4j2
@Controller
@RequestMapping("/account")
public class AccountRouter {

    private final AccountService service;

    @GetMapping("/login")
    private String login() { return "account/login"; }

    @GetMapping("/register")
    private String register(Model model) {
        log.debug("Rendering account page: register.html");

        model.addAttribute("accountRequest", AccountRequest.Base());

        return "account/register";
    }

    @PostMapping("/register")
    private String create(@ModelAttribute("accountRequest") AccountRequest accountRequest, BindingResult accountRequestBindingResult) {
        log.info("Saving account: {}", accountRequest);

        if (accountRequestBindingResult.hasErrors()) {
            log.error(accountRequestBindingResult.getAllErrors());

            return "account/register";  
        }

        try { service.create(accountRequest); }
        catch (BaseException exception) {
            handleException(accountRequestBindingResult, exception);

            return "account/register";
        }

        return "redirect:/account/management";
    }

    @GetMapping("/management")
    private String manage(Model model) {
        log.debug("Loading accounts");

        List<Account> accounts = service.findAllByName(Optional.empty(), Optional.empty());

        log.debug("Rendering account page: management.html");

        model.addAttribute("accounts", accounts);
        model.addAttribute("accountRequest", AccountRequest.Base());

        return "account/management";
    }

    @PostMapping("/management")
    private String update(@ModelAttribute("accountRequest") AccountRequest accountRequest, BindingResult accountRequestBindingResult) {
        log.info("Updating account: {}", accountRequest);

        if (accountRequestBindingResult.hasErrors()) {
            log.error(accountRequestBindingResult.getAllErrors());

            return "account/management";
        }

        try { service.update(accountRequest); }
        catch (BaseException exception) {
            handleException(accountRequestBindingResult, exception);
        }

        return "redirect:/account/management";
    }

    @DeleteMapping("/management/{id}")
    private void delete(Model model, @PathVariable Long id, @RequestBody String password) {
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

    private void handleException(BindingResult accountRequestBindingResult, BaseException exception) {
        log.error(exception.getType() + ": " + exception.getMessage());

        var field = "";

        if (exception.getClass().getSimpleName().equals("AccountException")) {
            field = switch (exception.getType()) {
                case "NoName" -> "name";
                case "WrongRepeatedPassword" -> "repeatedPassword";
                default -> "username";
            };
        }
        else field = "password";

        accountRequestBindingResult.rejectValue(field, exception.getClass().getSimpleName() + "." + exception.getType());
    }
}
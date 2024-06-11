package wiki.hf.presentation.controllers;

import wiki.hf.service.*;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import lombok.*;

@RequiredArgsConstructor

@RestController
@RequestMapping("api/token")
public class TokenController {
    private final TokenService service;

    @GetMapping
    public String getJwtToken (Authentication authentication) {
        return service.generateJwtToken(authentication);
    }
}
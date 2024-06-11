package wiki.hf.service;

import wiki.hf.domain.*;
import wiki.hf.persistence.repositories.*;

import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.core.*;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.transaction.annotation.*;
import org.springframework.stereotype.*;

import lombok.*;
import lombok.extern.log4j.*;

import java.security.*;
import java.time.*;
import java.time.temporal.*;
import java.util.*;
import java.util.stream.*;
import java.io.*;

@RequiredArgsConstructor
@Log4j2

@Service
@Transactional
public class TokenService {
    private static final char[] alphaNumericCharacters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final SecureRandom random = new SecureRandom();

    private final TokenRepository repository;

    private final JwtEncoder encoder;

    public String generateJwtToken(Authentication authentication) {
        Instant instant = Instant.now();
        String scope = authentication.getAuthorities().stream()
                                     .map(GrantedAuthority::getAuthority)
                                     .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                                          .issuer("https://hf.wiki")
                                          .issuedAt(instant)
                                          .expiresAt(instant.plus(1, ChronoUnit.HOURS))
                                          .subject(authentication.getName())
                                          .claim("scope", scope)
                                          .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private String createRandomValue(int length) {
        char[] value = new char[length];

        for (int i = 0; i < length; i++) {
            value[i] = alphaNumericCharacters[random.nextInt(alphaNumericCharacters.length)];
        }

        return new String(value);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public <T extends AbstractPersistable<? extends Serializable>> Token createToken(TokenType type) {
        String value;

        do {
            value = createRandomValue(5);
        }
        while (repository.existsByValue(value));

        var token = Token.builder().value(value).type(type).build();

        repository.saveAndFlush(token);
        return token;
    }
}
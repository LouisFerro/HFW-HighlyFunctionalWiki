package wiki.hf.service;

import org.springframework.data.jpa.domain.AbstractPersistable;
import wiki.hf.domain.*;
import wiki.hf.foundation.*;
import wiki.hf.persistence.repositories.*;
import wiki.hf.presentation.dataTransferObjects.AccountRequest;
import wiki.hf.service.exceptions.*;
import wiki.hf.service.policies.*;

import org.springframework.transaction.annotation.*;
import org.springframework.stereotype.Service;

import lombok.*;
import lombok.extern.log4j.*;

import java.io.Serializable;
import java.security.*;
import java.util.*;

@RequiredArgsConstructor
@Log4j2

@Service
@Transactional
public class TokenService {
    private static final char[] alphaNumericCharacters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final SecureRandom random = new SecureRandom();

    private final TokenRepository repository;

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

        do { value = createRandomValue(5); }
        while (repository.existsByValue(value));

        var token = Token.builder().value(value).type(type).build();

        repository.saveAndFlush(token);
        return token;
    }
}
package wiki.hf.configuration;

import org.springframework.stereotype.*;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

@Component
final class KeyGenerator {
    private KeyGenerator() {}

    static KeyPair generateRSAKey() {
        KeyPair keyPair;

        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception exception) {
            throw new IllegalStateException(exception);
        }

        return keyPair;
    }
}

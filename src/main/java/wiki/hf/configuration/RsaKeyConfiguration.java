package wiki.hf.configuration;

import org.springframework.boot.context.properties.*;

import java.security.interfaces.*;

@ConfigurationProperties(prefix = "rsa")
public record RsaKeyConfiguration (RSAPublicKey publicKey, RSAPrivateKey privateKey) {

}

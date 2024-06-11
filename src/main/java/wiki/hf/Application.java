package wiki.hf;

import wiki.hf.configuration.RsaKeyConfiguration;

import org.springframework.boot.*;
import org.springframework.boot.context.properties.*;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableConfigurationProperties(RsaKeyConfiguration .class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
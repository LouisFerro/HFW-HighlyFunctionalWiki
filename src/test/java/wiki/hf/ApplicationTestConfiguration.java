package wiki.hf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class ApplicationTestConfiguration {

    public static void main(String[] args) {
        SpringApplication.from(Application::main)
                         .with(ApplicationTestConfigurationContainer.class)
                         .run(args);
    }
}

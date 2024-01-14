package wiki.hf;

import com.github.dockerjava.api.model.*;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class ApplicationTestConfigurationContainer {

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        final int port = 5432;

        return new PostgreSQLContainer<>(DockerImageName.parse("postgres:16-alpine"))
                                        .withExposedPorts(6379)
                                        .withCreateContainerCmdModifier(cmd -> {
                                            cmd.withName("4EHIF-POS-Postgres");
                                            cmd.withHostConfig(
                                                    new HostConfig().withPortBindings(
                                                            new PortBinding(Ports.Binding.bindPort(port), new ExposedPort(port))));
                                        });
    }
}

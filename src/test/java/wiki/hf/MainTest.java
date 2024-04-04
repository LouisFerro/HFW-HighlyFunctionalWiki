package wiki.hf;

import com.github.dockerjava.api.model.*;

import org.springframework.context.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@Configuration
public class MainTest {

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        final int localPort = 65432;
        final int exposedPort = 5432;

        return new PostgreSQLContainer<>(DockerImageName.parse("postgres:16-alpine"))
                                         .withCreateContainerCmdModifier(cmd -> {
                                             cmd.withName("4EHIF-POS-Postgres");
                                             cmd.withHostConfig(
                                                 new HostConfig().withPortBindings(
                                                     new PortBinding(Ports.Binding.bindPort(localPort), new ExposedPort(exposedPort))));
                                         })
                                         .withReuse(true);
    }

    public static void main(String[] args) {
        SpringApplication.from(Application::main)
                .with(MainTest.class)
                .run(args);
    }
}
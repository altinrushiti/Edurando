package de.app_solutions.Edurando.testcontainers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class PostgresContainer {

    public static PostgreSQLContainer<?> container;

    @BeforeAll
    public static void setupContainer() {
        container = new PostgreSQLContainer<>("postgres:latest")
                .withUsername("postgres")
                .withPassword("password!")
                .withDatabaseName("EdurandoTestDb");
        container.start();
    }

    @AfterAll
    public static void teardownContainer() {
        if (container != null) {
            container.stop();
        }
    }
}


package de.app_solutions.Edurando.testcontainers;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class PostgresContainer extends PostgreSQLContainer<PostgresContainer> {
    private static final String IMAGE_VERSION = "postgres:11.1";
    private static PostgresContainer container;

    private PostgresContainer() {
        super(IMAGE_VERSION);
    }

    public static PostgresContainer getInstance() {
        if (container == null) {
            container = new PostgresContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("jdbc:postgresql://localhost:5432/postgres", container.getJdbcUrl());
        System.setProperty("postgres", container.getUsername());
        System.setProperty("Password", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}

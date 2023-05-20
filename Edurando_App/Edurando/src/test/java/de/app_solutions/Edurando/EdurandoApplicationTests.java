package de.app_solutions.Edurando;

import de.app_solutions.Edurando.testcontainers.PostgresContainer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
class EdurandoApplicationTests extends PostgresContainer {



	// > SP 2.2.6
	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) { //handles populating different properties at the right time
		registry.add("spring.datasource.url",container::getJdbcUrl);
		registry.add("spring.datasource.password",container::getPassword);
		registry.add("spring.datasource.username",container::getUsername);
	}

    @Test
    void contextLoads() {
		System.out.println("Context loaded");
    }

}

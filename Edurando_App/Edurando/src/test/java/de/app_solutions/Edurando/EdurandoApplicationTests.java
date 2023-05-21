package de.app_solutions.Edurando;

import de.app_solutions.Edurando.testcontainers.PostgresContainer;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@RunWith(SpringRunner.class)
class EdurandoApplicationTests {



	// > SP 2.2.6
	@ClassRule
	public static PostgreSQLContainer<PostgresContainer> postgreSQLContainer = PostgresContainer.getInstance();


	@Test
    void contextLoads() {
		System.out.println("Context loaded");
    }

}

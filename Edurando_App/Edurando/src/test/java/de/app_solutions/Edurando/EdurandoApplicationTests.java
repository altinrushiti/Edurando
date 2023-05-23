package de.app_solutions.Edurando;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
class EdurandoApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("Context loaded");
	}

}

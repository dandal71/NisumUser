package cl.nisum;

/**
 * Test instanciación controller
 */
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cl.nisum.controller.UserController;

@SpringBootTest
class NisumUsersApplicationTests {

	@Autowired
	private UserController userController;
	
	@Test
	void contextLoads() {
		 assertTrue( userController != null);
	}

}

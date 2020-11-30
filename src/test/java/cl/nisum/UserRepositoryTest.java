/**
 * 
 */
package cl.nisum;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import cl.nisum.model.entity.Phone;
import cl.nisum.model.entity.User;
import cl.nisum.model.repository.UserRepository;

/**
 * Repository test
 * 
 * @author Daniel E. Dalmagro
 *
 */
@SpringBootTest
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserRepositoryTest {

	   
	@Autowired
	private UserRepository userRespository;
			
	
	@Test
	@DisplayName("El usuario fue registrado exitosamente")
	public void saveNewUser() {
		User user = new User();
		user.setName("Daniel Test2");
		user.setUsername("danieltest");
		user.setEmail("ddalmagro@nisun.cl");
		user.setPassword("DDD1111");
		
		Phone phone = new Phone();
		phone.setCityCode("341");
		phone.setCountryCode("54");
		phone.setNumber("3332221112");
		User userBD = this.userRespository.save(user);
		
		user.addPhone(phone);
		
		assertTrue(userBD.getId() != null);		
	} 
		
	
}

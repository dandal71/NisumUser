package cl.nisum.model.service;

import java.util.Optional;
import java.util.UUID;
import cl.nisum.model.entity.User;

/**
 * User service interface
 * 
 * @author Daniel E. Dalmagro
 *
 */
public interface UserService {
			
	public Iterable<User> findAll();
	
	public Optional<User> findById(UUID id);
	
	public User save(User user);
	
	public void deleteById(UUID id);
	
	public Optional<User> getUserByUsername(String username);
	
	public Optional<User> getUserByEmail(String email);
	
	public Iterable<User> getActiveUsers();
	

}

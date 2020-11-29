package cl.nisum.model.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import cl.nisum.model.entity.User;

public interface UserRepository extends CrudRepository<User, UUID> {

	/**
	 * Get users by username
	 * 
	 * @param usaername username to filter
	 * @return uses list
	 */
	public Iterable<User> findByUsername(String username);
	
	
	/**
	 * Get user by email field
	 * @param email email to filter
	 * @return user list
	 */
	public Iterable<User> findByEmail(String email);
	
	
	/**
	 * Get users with status greater than a specific value
	 * @param status status to filter
	 * @return
	 */
	public Iterable<User> findByStatusGreaterThan(Integer status);
	

}

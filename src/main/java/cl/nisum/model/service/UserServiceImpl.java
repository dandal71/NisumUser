/**
 * 
 */
package cl.nisum.model.service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.nisum.error.UserException;
import cl.nisum.model.entity.Phone;
import cl.nisum.model.entity.User;
import cl.nisum.model.repository.UserRepository;
import cl.nisum.utils.Const;

/**
 * @author Daniel E. Dalmagro
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private final String passCheck = "(?=.*[0-9][0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,}";
	private final String emailCheck = "^(.+)@(.+cl)$";

	@Autowired
	private UserRepository userRespository;
		
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<User> findAll() {		
		return this.userRespository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(UUID id) {		 
		return this.userRespository.findById(id);
	}

	@Override
	@Transactional
	public User save(User user) {		
		//PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		if (user.getPassword() != null) {
			if (!user.getPassword().matches(passCheck)) {
				throw new UserException("El password debe contener: Mayúscula, letras minúsculas, y dos numeros", HttpStatus.BAD_REQUEST);
			} else {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
			}
		}
				
		if (this.isDuplicatedUsername(user.getUsername(), user.getId())) {
			throw new UserException("El nombre de usuario indicado ya está en uso", HttpStatus.BAD_REQUEST);
		} 
		
		if (this.isDuplicatedEmail(user.getEmail(), user.getId())) {
			throw new UserException("Un usuario ya ha utilizado esta cuenta de correo", HttpStatus.BAD_REQUEST);
		} else {
			if (!user.getEmail().matches(emailCheck)){
				throw new UserException("Formato de email inválido. Ingrese un mail de la forma: aaaaaa@algo.cl", HttpStatus.BAD_REQUEST);
			}
		}
		
		//new user
		if (user.getId() == null) {
			return  this.userRespository.save(user);	
		} else {//update existing user
			return this.updateUser(user);
		}
	}

	@Override
	@Transactional
	public void deleteById(UUID id) {
		this.userRespository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> getUserByUsername(String username) {		
		List<User> users = (List<User>)this.userRespository.findByUsername(username);
		
		if (!users.isEmpty()) {
			return Optional.of(users.get(0));
		}
		return Optional.empty();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> getUserByEmail(String email) {
		List<User> users = (List<User>)this.userRespository.findByEmail(email);
		
		if (!users.isEmpty()) {
			return Optional.of(users.get(0));
		}
		return Optional.empty();
	}

	@Override
	public Iterable<User> getActiveUsers() {		
		return this.userRespository.findByStatusGreaterThan(Const.USER_STATUS_ACTIVE);
	}

	/**
	 * Check duplicated email
	 * 
	 * @param email Email to check
	 * @param id User id
	 * @return true o false
	 */
	private boolean isDuplicatedUsername(String username, UUID id) {
		List<User> users = (List<User>) this.userRespository.findByUsername(username);
		if (users.isEmpty()) {
			return false;
		} else {
			User user = users.get(0);
			if (id != null) {
				if (user.getId().equals(id)) {
					return false; 
				}
			}
			return true;
		}				
	}

	
	/**
	 * Check duplicated username
	 * 
	 * @param email Emial to check
	 * @param id User id
	 * @return true o false
	 */
	private boolean isDuplicatedEmail(String email, UUID id) {
		List<User> users = (List<User>) this.userRespository.findByEmail(email);
		if (users.isEmpty()) {
			return false;
		} else {
			User user = users.get(0);//No puede haber mas de 1
			if (id != null) {
				if (user.getId().equals(id)) {
					return false; //NO es duplicado porque es el mismo usuario. Sirve para un update
				}
			}
			return true;
		}				
	}

	
	/**
	 * Update user
	 * @param user
	 * @return
	 * @throws UserException
	 */
	private User updateUser(User user) throws UserException {
		Optional<User> optUser = this.findById(user.getId());
		User userBD;
		
		if (!optUser.isPresent()) {
			 throw new UserException("Usuario inexistente", HttpStatus.NOT_FOUND);
		}
		userBD = optUser.get();
		
		userBD.setName(user.getName()); 
		userBD.setUsername(user.getUsername());		
		userBD.setEmail(user.getEmail());		
		userBD.setToken(user.getToken());
		
		if (user.getPassword() != null) {
			if (!user.getPassword().matches(passCheck)) {
				throw new UserException("El password debe contener: Mayúscula, letras minúsculas, y dos numeros");
			}
			userBD.setPassword(user.getPassword());
		}
		
		List<Phone> deletedPhones = new ArrayList<Phone>();		
		//Check deleted phones
		userBD.getPhones().stream()
				.filter((p)->!user.getPhones().contains(p)) 
				.forEach(deletedPhones::add);											
		deletedPhones.forEach(userBD::removePhone); 
		
		//Add new phones
		userBD.setPhones(user.getPhones());
	
		return userBD;
	}
	
}

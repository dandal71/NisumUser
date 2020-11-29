package cl.nisum.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cl.nisum.error.UserException;
import cl.nisum.model.entity.User;
import cl.nisum.model.service.UserService;

/**
 * User rest controller
 * @author Daniel E. Dalmagro
 *
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getEntidad(@PathVariable UUID id) { 
		
		Optional<User> opt = this.userService.findById(id);
		if (!opt.isPresent()) {
			throw new UserException("Usuario inexistente", HttpStatus.NOT_FOUND); 
		}
		
		return ResponseEntity.ok().body(opt.get());
	}
	
	/**
	 * Returns list of users
	 * @return
	 */
	@GetMapping("/list")
	public ResponseEntity<?> listUsers() {
		ApiResponse response = new ApiResponse(HttpStatus.OK, (List<User>)userService.findAll());
		response.setMessage("Entidades obtenidas éxitosamente");
		return ApiResponseBuilder.build(response);		
	}
	
	/**
	 * Create or update a user. If user has a not null id, an update will be performed. 
	 * 
	 * @param user User
	 * @return Persisted user
	 */
	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody User user) { 
		String message;
		
		if (user.getId() == null)
			message =  "Usuario creado con éxito";
		else 
			message = "Usuario actualizado con éxito";
	
		User userBD = this.userService.save(user);		
		return ApiResponseBuilder.build(new ApiResponse(HttpStatus.OK, message, userBD));
		
	}
	
	
	
}

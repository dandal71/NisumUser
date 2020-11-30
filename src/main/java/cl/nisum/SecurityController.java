package cl.nisum;

import java.time.LocalDateTime;
import java.util.Date;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.nisum.controller.ApiResponse;
import cl.nisum.controller.ApiResponseBuilder;
import cl.nisum.error.UserException;
import cl.nisum.model.entity.User;
import cl.nisum.model.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class SecurityController {
		
	private final String PREFIX = "Bearer ";
	private final String SECRET = "mySecretKey";
	private final String AUTHORITIES = "authorities";


	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam("username") String username, @RequestParam("password") String password) {
		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(18, new SecureRandom());		
		Optional<User> opt = userService.getUserByUsername(username);
		
		if (!opt.isPresent()) {
			throw new UserException("Usuario inexistente", HttpStatus.FORBIDDEN); 
		}
		User user = opt.get();	

		if (passwordEncoder.matches(password, user.getPassword())) {
			String token = getJWTToken(username);
			user.setToken(token);
			user.setLastLogin(LocalDateTime.now());
			User userBD = this.userService.save(user);
			ApiResponse response = new ApiResponse(HttpStatus.OK, "Login Exitoso", userBD);
			return ApiResponseBuilder.build(response);			
		} else {
			throw new UserException("Nombre de usuairo o password inexistente", HttpStatus.FORBIDDEN);
		}
	}

	private String getJWTToken(String username) {		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("nisumJWT")
				.setSubject(username)
				.claim(AUTHORITIES,
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						SECRET.getBytes()).compact();

		return PREFIX + token;
	}
}

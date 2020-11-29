package cl.nisum.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import cl.nisum.model.entity.User;

/**
 * Class for show api erros like validation or not found
 * 
 * @author Daniel E. Dalmagro
 *
 */
public class ApiResponse {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestampResponse;

	private HttpStatus status;

	private String message;

	private User user;
	
	private List<User> users;
	
	private List<String> errors;
	

	/**
	 * Construct
	 * 
	 * @param status Http status
	 * @param message Response menssage
	 * @param user user attach to response
	 */
	public ApiResponse(HttpStatus status, String message, User user) {
		super();
		this.timestampResponse = LocalDateTime.now();
		this.status = status;
		this.message = message;
		this.user = user;
	}

	/**
	 * Construct 
	 * 
	 * @param status Http status
	 * @param message Message response
	 * @param errors error list
	 */
	public ApiResponse(HttpStatus status, String message, List<String> errors) {
		super();
		this.timestampResponse = LocalDateTime.now();
		this.status = status;
		this.message = message;
		this.errors = errors;
	}
	
	
	/**
	 * Construct
	 * 
	 * @param status Http status
	 * @param message Response menssage
	 * @param user user attach to response
	 */
	public ApiResponse(HttpStatus status, List<User> users) {
		super();
		this.timestampResponse = LocalDateTime.now();
		this.status = status;		
		this.users = users;
	}

	public LocalDateTime getTimestampResponse() {
		return timestampResponse;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestampResponse = timestamp;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	
}

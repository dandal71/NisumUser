package cl.nisum.error;

import org.springframework.http.HttpStatus;

/**
 * Excpetion for user crud.
 * 
 * @author Daniel E. Dalmagro
 *
 */
public class UserException extends RuntimeException {
		
	private static final long serialVersionUID = 1L;
	private String message;
	private HttpStatus httpStatus;
	
	public UserException() {		
	}
	
	public UserException(String message) {
		this.message = message;
	}
	
	public UserException(String message, HttpStatus status) {
		this.message = message;
		this.httpStatus = status;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	

}

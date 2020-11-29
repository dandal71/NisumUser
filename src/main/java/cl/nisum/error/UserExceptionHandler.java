package cl.nisum.error;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cl.nisum.controller.ApiResponse;
import cl.nisum.controller.ApiResponseBuilder;

/**
 * Handler exception
 * 
 * @author Daniel E. Dalmagro
 *
 */
@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Catch validation errors with @valid anotation
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<String> details = new ArrayList<String>();
		details = ex.getBindingResult()
                      .getFieldErrors()
                      .stream()
                      .map(error -> error.getObjectName()+ " : " +error.getDefaultMessage())
                      .collect(Collectors.toList());
          
		ApiResponse err = new ApiResponse(HttpStatus.BAD_REQUEST, "Errores de validación", details);
          
		return ApiResponseBuilder.build(err);
	}
	
	/**
	 * 
	 * @param ex
	 * @param headers
	 * @param status
	 * @param request
	 * @return
	 */
	@ExceptionHandler(UserException.class)
	protected ResponseEntity<Object> handlerDuplicatedEmail(UserException ex) {
						 
		ApiResponse err = new ApiResponse(ex.getHttpStatus(), "Respuesta Servidor", Arrays.asList(ex.getMessage()));
          
		return ApiResponseBuilder.build(err);
	}

}

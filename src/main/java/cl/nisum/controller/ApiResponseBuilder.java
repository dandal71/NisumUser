package cl.nisum.controller;

import org.springframework.http.ResponseEntity;

public class ApiResponseBuilder {
	
	public static ResponseEntity<Object> build(ApiResponse apiResponse) {
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

}

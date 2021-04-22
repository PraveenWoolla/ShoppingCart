package com.springBoot.microservices.shoppingcart;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


//@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
     @ExceptionHandler(Exception.class)
     public ResponseEntity<Object> MainException()
     {
    	 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
     }
     
}

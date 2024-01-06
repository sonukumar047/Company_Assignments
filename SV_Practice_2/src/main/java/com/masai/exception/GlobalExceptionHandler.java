package com.masai.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserException.class)
	ResponseEntity<ExceptionDefination> userNotFoundException(UserException ex, WebRequest wb){
		return new ResponseEntity<ExceptionDefination>(
				new ExceptionDefination(
						LocalDateTime.now(),
						ex.getMessage(),
						wb.getDescription(false)
						), HttpStatus.NOT_FOUND
				
				);
	}
	
	@ExceptionHandler(Exception.class)
	ResponseEntity<ExceptionDefination> globalException(Exception ex, WebRequest wb){
		return new ResponseEntity<ExceptionDefination>(
				new ExceptionDefination(
						LocalDateTime.now(),
						ex.getMessage(),
						wb.getDescription(false)
						), HttpStatus.NOT_FOUND
				
				);
	}
}

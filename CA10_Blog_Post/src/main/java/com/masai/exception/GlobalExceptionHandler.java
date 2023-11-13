package com.masai.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(PostNotFoundException.class)
	ResponseEntity<ExceptionDefination> postNotFoundExcption(PostNotFoundException ex, WebRequest wb){
		return new ResponseEntity<ExceptionDefination>(
				new ExceptionDefination(
						LocalDateTime.now(),
						ex.getMessage(),
						wb.getDescription(false)
						), HttpStatus.BAD_REQUEST
				
				);
	}
	
	@ExceptionHandler(Exception.class)
	ResponseEntity<ExceptionDefination> globalException(Exception ex, WebRequest wb){
		return new ResponseEntity<ExceptionDefination>(
				new ExceptionDefination(
						LocalDateTime.now(),
						ex.getMessage(),
						wb.getDescription(false)
						), HttpStatus.BAD_REQUEST
				);
	}
	

	@ExceptionHandler(UserNotFoundException.class)
	ResponseEntity<ExceptionDefination> userNotFound(UserNotFoundException ex, WebRequest wb){
		return new ResponseEntity<ExceptionDefination>(
				new ExceptionDefination(
						LocalDateTime.now(),
						ex.getMessage(),
						wb.getDescription(false)
						), HttpStatus.BAD_REQUEST
				);
	}
}

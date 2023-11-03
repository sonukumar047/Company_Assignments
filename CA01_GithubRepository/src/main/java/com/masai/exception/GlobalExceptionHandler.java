package com.masai.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RepositoryNotFound.class)
	public ResponseEntity<ExceptionDefination> repositoryNotFound(RepositoryNotFound ex, WebRequest wb){
		
		ExceptionDefination exception = new ExceptionDefination();
		exception.setOccuredAt(LocalDateTime.now());
		exception.setMessage(ex.getMessage());
		exception.setDetails(wb.getDescription(false));
		
		return new ResponseEntity<ExceptionDefination>(exception, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionDefination> globalException(Exception ex, WebRequest wb){
		
		ExceptionDefination exception = new ExceptionDefination();
		exception.setOccuredAt(LocalDateTime.now());
		exception.setMessage(ex.getMessage());
		exception.setDetails(wb.getDescription(false));
		
		return new ResponseEntity<ExceptionDefination>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

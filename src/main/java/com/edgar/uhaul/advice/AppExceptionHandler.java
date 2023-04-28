package com.edgar.uhaul.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.edgar.uhaul.exceptions.LocationAlreadyExistsException;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(LocationAlreadyExistsException.class)
	public ResponseEntity<Object> handleEventDoesntExist(LocationAlreadyExistsException ex, WebRequest request) {

		return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}

}

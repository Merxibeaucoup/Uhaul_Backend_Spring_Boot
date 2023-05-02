package com.edgar.uhaul.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.edgar.uhaul.exceptions.LocationAlreadyExistsException;
import com.edgar.uhaul.exceptions.LocationCantHaveStorageUnitsException;
import com.edgar.uhaul.exceptions.LocationDoesntExistException;
import com.edgar.uhaul.exceptions.StorageAlreadyExistsException;
import com.edgar.uhaul.exceptions.TruckAlreadyExistsException;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(LocationAlreadyExistsException.class)
	public ResponseEntity<Object> handleLocationAlreadyExist(LocationAlreadyExistsException ex, WebRequest request) {

		return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LocationDoesntExistException.class)
	public ResponseEntity<Object> handleLocationDoesntExist(LocationDoesntExistException ex, WebRequest request) {

		return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(LocationCantHaveStorageUnitsException.class)
	public ResponseEntity<Object> handleLocationCantHaveStorageUnits(LocationCantHaveStorageUnitsException ex, WebRequest request) {

		return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(StorageAlreadyExistsException.class)
	public ResponseEntity<Object> handleStorageAlreadyExistsException(StorageAlreadyExistsException ex, WebRequest request) {

		return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TruckAlreadyExistsException.class)
	public ResponseEntity<Object> handleTruckAlreadyExistsException(TruckAlreadyExistsException ex, WebRequest request) {

		return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}
	
	

}

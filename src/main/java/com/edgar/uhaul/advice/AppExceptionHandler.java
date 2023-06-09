package com.edgar.uhaul.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.edgar.uhaul.exceptions.DateIsNotTodayOrAfterTodayException;
import com.edgar.uhaul.exceptions.LocationAlreadyExistsException;
import com.edgar.uhaul.exceptions.LocationCantHaveStorageUnitsException;
import com.edgar.uhaul.exceptions.LocationDoesntExistException;
import com.edgar.uhaul.exceptions.PackingSupplyAlreadyExistsException;
import com.edgar.uhaul.exceptions.PackingSupplyDoesntExistException;
import com.edgar.uhaul.exceptions.StorageAlreadyExistsException;
import com.edgar.uhaul.exceptions.StorageInsuranceAlreadyExistsException;
import com.edgar.uhaul.exceptions.StorageInsuranceDoesntExistException;
import com.edgar.uhaul.exceptions.StorageOrderCantBeCreatedException;
import com.edgar.uhaul.exceptions.StorageOrderDoesNotExistException;
import com.edgar.uhaul.exceptions.TruckAlreadyExistsException;
import com.edgar.uhaul.exceptions.TruckDoesntExistException;
import com.edgar.uhaul.exceptions.TruckOrderCanNotBeCancelledException;
import com.edgar.uhaul.exceptions.TruckOrderDoesntExistException;

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
	
	@ExceptionHandler(TruckDoesntExistException.class)
	public ResponseEntity<Object> handleTruckDoesntExistException(TruckDoesntExistException ex, WebRequest request) {
		return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PackingSupplyAlreadyExistsException.class)
	public ResponseEntity<Object> handleLocationAlreadyExist(PackingSupplyAlreadyExistsException ex, WebRequest request) {
		return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PackingSupplyDoesntExistException.class)
	public ResponseEntity<Object> handleTruckDoesntExistException(PackingSupplyDoesntExistException ex, WebRequest request) {
		return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DateIsNotTodayOrAfterTodayException.class)
	public ResponseEntity<Object> handleDateIsNotTodayOrAfterTodayException(DateIsNotTodayOrAfterTodayException ex, WebRequest request) {
		return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TruckOrderDoesntExistException.class)
	public ResponseEntity<Object> handleTruckOrderDoesntExistException(TruckOrderDoesntExistException ex, WebRequest request) {
		return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()),
				HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(TruckOrderCanNotBeCancelledException.class)
	public ResponseEntity<Object> handleTruckOrderCanNotBeCancelledException(TruckOrderCanNotBeCancelledException ex, WebRequest request) {
		return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(StorageInsuranceAlreadyExistsException.class)
	public ResponseEntity<Object> handleStorageInsuranceAlreadyExistsException(StorageInsuranceAlreadyExistsException ex, WebRequest request) {
		return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(StorageInsuranceDoesntExistException.class)
	public ResponseEntity<Object> handleStorageInsuranceDoesntExistException(StorageInsuranceDoesntExistException ex, WebRequest request) {
		return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(StorageOrderDoesNotExistException.class)
	public ResponseEntity<Object> handleStorageOrderDoesNotExistException(StorageOrderDoesNotExistException ex, WebRequest request) {
		return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(StorageOrderCantBeCreatedException.class)
	public ResponseEntity<Object> handleStorageOrderCantBeCreatedException(StorageOrderCantBeCreatedException ex, WebRequest request) {
		return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	

}

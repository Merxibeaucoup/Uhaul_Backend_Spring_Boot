package com.edgar.uhaul.exceptions;

public class LocationAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public LocationAlreadyExistsException(String message) {
		super(message);
	}

}

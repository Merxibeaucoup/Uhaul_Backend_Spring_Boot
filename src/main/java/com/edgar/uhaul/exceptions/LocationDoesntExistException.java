package com.edgar.uhaul.exceptions;

public class LocationDoesntExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LocationDoesntExistException(String message) {
		super(message);
	}

}

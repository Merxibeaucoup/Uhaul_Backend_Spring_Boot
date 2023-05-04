package com.edgar.uhaul.exceptions;

public class TruckDoesntExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TruckDoesntExistException(String message) {
		super(message);
	}

}

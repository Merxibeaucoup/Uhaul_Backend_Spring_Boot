package com.edgar.uhaul.exceptions;

public class TruckAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TruckAlreadyExistsException(String message) {
		super(message);
	}

}

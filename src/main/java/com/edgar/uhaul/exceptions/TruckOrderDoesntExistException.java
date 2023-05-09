package com.edgar.uhaul.exceptions;

public class TruckOrderDoesntExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TruckOrderDoesntExistException(String message) {
		super(message);
	}
}

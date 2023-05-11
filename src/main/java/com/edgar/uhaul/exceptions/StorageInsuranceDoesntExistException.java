package com.edgar.uhaul.exceptions;

public class StorageInsuranceDoesntExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StorageInsuranceDoesntExistException(String message) {
		super(message);
	}

}

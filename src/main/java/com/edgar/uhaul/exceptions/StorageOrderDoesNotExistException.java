package com.edgar.uhaul.exceptions;

public class StorageOrderDoesNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StorageOrderDoesNotExistException(String message) {
		super(message);
	}
}

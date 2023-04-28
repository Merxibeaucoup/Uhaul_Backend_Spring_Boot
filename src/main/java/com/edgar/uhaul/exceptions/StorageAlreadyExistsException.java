package com.edgar.uhaul.exceptions;

public class StorageAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StorageAlreadyExistsException(String message) {
		super(message);
	}
}

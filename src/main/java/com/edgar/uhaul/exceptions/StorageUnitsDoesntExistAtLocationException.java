package com.edgar.uhaul.exceptions;

public class StorageUnitsDoesntExistAtLocationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StorageUnitsDoesntExistAtLocationException(String message) {
		super(message);
	}
}

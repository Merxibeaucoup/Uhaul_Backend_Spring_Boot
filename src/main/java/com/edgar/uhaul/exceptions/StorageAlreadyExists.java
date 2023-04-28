package com.edgar.uhaul.exceptions;

public class StorageAlreadyExists extends RuntimeException {

	
	
	private static final long serialVersionUID = 1L;

	public StorageAlreadyExists(String message) {
		super(message);
	}
}

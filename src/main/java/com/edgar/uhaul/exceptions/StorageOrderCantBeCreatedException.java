package com.edgar.uhaul.exceptions;

public class StorageOrderCantBeCreatedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StorageOrderCantBeCreatedException(String message) {
		super(message);
	}
}

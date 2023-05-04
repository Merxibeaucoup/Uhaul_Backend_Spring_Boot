package com.edgar.uhaul.exceptions;

public class PackingSupplyAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PackingSupplyAlreadyExistsException(String message) {
		super(message);
	}
}

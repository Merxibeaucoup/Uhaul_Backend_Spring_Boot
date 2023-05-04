package com.edgar.uhaul.exceptions;

public class PackingSupplyDoesntExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PackingSupplyDoesntExistException(String message) {
		super(message);
	}

}

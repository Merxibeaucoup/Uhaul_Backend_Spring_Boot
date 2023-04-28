package com.edgar.uhaul.exceptions;

public class LocationCantHaveStorageUnitsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LocationCantHaveStorageUnitsException(String message) {
		super(message);
	}
}

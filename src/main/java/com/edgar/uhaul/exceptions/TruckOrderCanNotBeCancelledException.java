package com.edgar.uhaul.exceptions;

public class TruckOrderCanNotBeCancelledException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TruckOrderCanNotBeCancelledException(String message) {
		super(message);
	}

}

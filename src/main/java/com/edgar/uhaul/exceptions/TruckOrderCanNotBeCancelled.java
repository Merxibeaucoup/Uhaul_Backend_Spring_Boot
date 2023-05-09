package com.edgar.uhaul.exceptions;

public class TruckOrderCanNotBeCancelled extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TruckOrderCanNotBeCancelled(String message) {
		super(message);
	}

}

package com.edgar.uhaul.exceptions;

public class DateIsNotTodayOrAfterTodayException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DateIsNotTodayOrAfterTodayException(String message) {
		super(message);
	}
}

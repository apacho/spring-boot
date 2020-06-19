package com.codingtest.registation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No employee found!")
public class RecordNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public RecordNotFoundException(String message) {
		super(message);
	}
	
	public RecordNotFoundException(String message, Throwable t) {
		super(message, t);
	}
}

package com.tabcorp.exceptions;

import java.io.Serializable;

public class InvalidBetTypeException extends RuntimeException implements Serializable{

	
	public InvalidBetTypeException(String betType) {
		super(betType);
		
	}

	private static final long serialVersionUID = 1L;
	
}

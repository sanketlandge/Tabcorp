package com.tabcorp.exceptions;

import java.time.LocalDateTime;


public class ErrorDetails {

	private LocalDateTime timestamp;
	private String error;
	private String details;

	public ErrorDetails(LocalDateTime localDateTime, String message, String details) {
		super();
		this.timestamp = localDateTime;
		this.error = message;
		this.details = details;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}


}
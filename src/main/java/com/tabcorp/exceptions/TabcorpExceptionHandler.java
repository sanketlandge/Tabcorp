package com.tabcorp.exceptions;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class TabcorpExceptionHandler extends ResponseEntityExceptionHandler {

	private final Logger log = LoggerFactory.getLogger(TabcorpExceptionHandler.class);

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler({DateTimeException.class, NumberFormatException.class , DateTimeParseException.class})
	public ResponseEntity<Object> handleInvalidInput(
			RuntimeException ex) {
		log.info("[Exception Handler Execution Started]");
		ErrorDetails error = new ErrorDetails(LocalDateTime.now(), "Invalid Input" , ex.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(InvalidBetTypeException.class)
	public ResponseEntity<Object> handleParsingInvalidInputData(
			RuntimeException ex) {
		log.info("[Exception Handler Execution Started]");
		ErrorDetails error = new ErrorDetails(LocalDateTime.now(), "Invalid Bet Type" , ex.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

}
package com.yash.student.exception;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

	@ExceptionHandler()
	public ResponseEntity<ExceptionResponse> handleResourceNotFound(RecordNotFoundException exception) {

		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND.toString());
		error.setTimestamp(LocalDate.now());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

		/*
		 * @ExceptionHandler(Exception.class)
		 * 
		 * @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
		 * public @ResponseBody ExceptionResponse handleException(final
		 * Exception exception, final HttpServletRequest request) {
		 * 
		 * ExceptionResponse error = new ExceptionResponse();
		 * error.setErrorMessage(exception.getMessage());
		 * error.callerURL(request.getRequestURI());
		 * 
		 * return error; }
		 */

	}

}

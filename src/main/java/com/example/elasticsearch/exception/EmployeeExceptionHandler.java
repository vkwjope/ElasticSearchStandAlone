package com.example.elasticsearch.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoRecordsFoundException.class)
	public ResponseEntity<ExceptionResponse> resourceNotFound(java.lang.Exception ex, WebRequest request) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), httpStatus.value(),
				httpStatus.getReasonPhrase(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, httpStatus);
	}

}

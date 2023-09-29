package com.student.StudentCrudAppWithUI.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(StudentDataAccessException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(StudentDataAccessException accessException, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(accessException.getLocalizedMessage());
		ExceptionResponse error = new ExceptionResponse("Record Not Found", details);
		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(exception.getLocalizedMessage());
		ExceptionResponse error = new ExceptionResponse("Server Error", details);
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException argumentNotValidException,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : argumentNotValidException.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ExceptionResponse errorResponse = new ExceptionResponse("Validation Failed", details);
		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}
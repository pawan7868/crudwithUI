package com.student.StudentCrudAppWithUI.exception;

import java.util.List;

public class ExceptionResponse {
//message about an error
private String message;
//about errors in API request processing
private List<String> details;
public ExceptionResponse(String message, List<String> details) {
	super();
	this.message = message;
	this.details = details;
}
public String getMessage() {
	return message;
}

public List<String> getDetails() {
	return details;
}


}

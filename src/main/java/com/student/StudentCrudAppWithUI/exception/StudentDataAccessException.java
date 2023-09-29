package com.student.StudentCrudAppWithUI.exception;

public class StudentDataAccessException extends Exception{

private static final long serialVersionUID = -4798965926484615958L;
	
	public StudentDataAccessException(String errorMessage) {
		super(errorMessage);
	}
}

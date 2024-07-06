package com.student.exceptions;

public class StudentIdNotFoundException extends RuntimeException{

	public StudentIdNotFoundException(String msg) {
		super(msg);
	}
}

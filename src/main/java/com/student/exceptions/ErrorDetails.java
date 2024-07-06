package com.student.exceptions;

import java.time.LocalDateTime;

public class ErrorDetails {

	private LocalDateTime timeStamp;
	private String message;
	private String errorCode;

	public ErrorDetails(LocalDateTime timeStamp, String message, String errorCode) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.errorCode = errorCode;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}

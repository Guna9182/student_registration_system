package com.student.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//import static com.student.StudentRegistrationSystemApplication.myLogger;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(StudentIdNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleStudentIdNotFound(Exception e) {

		String message = e.getMessage();

		ErrorDetails details = new ErrorDetails(LocalDateTime.now(), message, "STUDENT_ID_NOT_FOUND");
//		myLogger.warn("Student not found with this Id.");
		return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(StudentRollNoAlreadyExistsException.class)
	public ResponseEntity<ErrorDetails> handleStudentRollNoAlredyExists(Exception e) {

		ErrorDetails details = new ErrorDetails(LocalDateTime.now(), e.getMessage(),
				"Already Student Roll No Exists..");
//		myLogger.warn("Already student with this Roll No exists.");
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.ALREADY_REPORTED);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			                                                HttpHeaders headers,
			                                                HttpStatusCode status,
			                                                WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		allErrors.forEach((error)->{
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}
}

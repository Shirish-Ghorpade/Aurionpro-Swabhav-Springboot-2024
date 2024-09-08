package com.aurionpro.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.aurionpro.model.error.StudentErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleStudentNotFoundException(StudentNotFoundException exception) {
//		we create the dto (data transfer object) file for showing the error.
//		Creating the object of that dto
		StudentErrorResponse error = new StudentErrorResponse();

//		setting the status code
		error.setStatusCode(HttpStatus.NOT_FOUND.value());
//		setting the error message
		error.setErrorMessage(exception.getMessage());
//		setting the time stamp
		error.setTimestamp(System.currentTimeMillis());
		
//		return the response page
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleStudentException(MethodArgumentTypeMismatchException exception) {
		StudentErrorResponse error = new StudentErrorResponse();
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage("Roll Number must be an integer");
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleStudentException(MethodArgumentNotValidException exception) {
		StudentErrorResponse error = new StudentErrorResponse();
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage("Something is went wrong");
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}

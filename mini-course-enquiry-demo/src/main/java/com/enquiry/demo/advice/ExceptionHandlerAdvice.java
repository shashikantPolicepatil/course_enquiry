package com.enquiry.demo.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.enquiry.demo.dto.ResponseData;
import com.enquiry.demo.exception.ResetPasswordException;


@RestControllerAdvice
public class ExceptionHandlerAdvice {

	/*@ExceptionHandler({FileWriteException.class,DocumentException.class})
	public ResponseEntity<ResponseData> handleFileWriteException(FileWriteException ex,DocumentException ex2) {
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
				.body(new ResponseData(HttpStatus.EXPECTATION_FAILED.toString(), ex.getMessage()));
	}*/

	//@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<ResponseData> handleMaxSizeException(MaxUploadSizeExceededException exc) {
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
				.body(new ResponseData(HttpStatus.EXPECTATION_FAILED.toString(), "File too large!"));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> validationError(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResetPasswordException.class)
	public ResponseEntity<?> resetPasswordException(ResetPasswordException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseData(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage()));
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<?> usernameNotFoundException(UsernameNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseData(HttpStatus.OK.toString(), ex.getMessage()));
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> duplicateUsernameException(RuntimeException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ResponseData(HttpStatus.BAD_REQUEST.toString(), ex.getMessage()));
	}
}

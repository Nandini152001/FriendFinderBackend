package com.example.friendfinder.exceptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.friendfinder.entity.ErrorResponse;

@ControllerAdvice
public class FriendFinderExceptionHandler {
	
	@ExceptionHandler(ComplaintNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(ComplaintNotFoundException exception)
	{
	   //Create ErrorResponse Obj
	   ErrorResponse error = new ErrorResponse();
	
	   //Update error obj with values
	   error.setStatus(HttpStatus.NOT_FOUND.value()); //404 not found
	   error.setMessage(exception.getMessage()); //get message from exception
	   error.setTimeStamp(LocalDateTime.now()); //system time
	
	//return responseEntity obj
	return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); //404 not found
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(UserNotFoundException exception)
	{
		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());//404 NOT FOUND
		error.setMessage(exception.getMessage()); //get message from exception
		error.setTimeStamp(LocalDateTime.now()); //system time
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PollNotExistException.class)
	public ResponseEntity<ErrorResponse> handleException(PollNotExistException exception)
	{
		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());//404 NOT FOUND
		error.setMessage(exception.getMessage()); //get message from exception
		error.setTimeStamp(LocalDateTime.now()); //system time
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ErrorResponse> handleException(InvalidCredentialsException exception)
	{
		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());//404 NOT FOUND
		error.setMessage(exception.getMessage()); //get message from exception
		error.setTimeStamp(LocalDateTime.now()); //system time
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DuplicatePollParticipationException.class)
	public ResponseEntity<ErrorResponse> handleException(DuplicatePollParticipationException exception)
	{
		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());//404 NOT FOUND
		error.setMessage(exception.getMessage()); //get message from exception
		error.setTimeStamp(LocalDateTime.now()); //system time
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PostNotFoundById.class)
	public ResponseEntity<ErrorResponse> handleException(PostNotFoundById exception) {
		// Create ErrorResponse Obj
		ErrorResponse error = new ErrorResponse();
		
		// update error obj with values
		error.setStatus((HttpStatus.NOT_FOUND.value()));// 404 not found
		error.setMessage(exception.getMessage()); //get message from exception
		error.setTimeStamp(LocalDateTime.now()); // system time

		// return responseEntity obj
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);  //404 Not found
	}
	
	@ExceptionHandler(CommentNotFound.class)
	public ResponseEntity<ErrorResponse> handleException(CommentNotFound exception) {
		// Create ErrorResponse Obj
		ErrorResponse error = new ErrorResponse();
		
		// update error obj with values
		error.setStatus((HttpStatus.NOT_FOUND.value()));// 404 not found
		error.setMessage(exception.getMessage()); //get message from exception
		error.setTimeStamp(LocalDateTime.now()); // system time

		// return responseEntity obj
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);  //404 Not found
	}
}

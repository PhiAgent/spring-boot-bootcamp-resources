package com.ltp.contacts.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ltp.contacts.exception.ContactNotFoundException;
import com.ltp.contacts.exception.ErrorResponse;

@ControllerAdvice//this allows this class to act as the global exception handler
public class ApplicationExceptionHandler {
  @ExceptionHandler(ContactNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleContactNotFoundException(ContactNotFoundException e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }
}

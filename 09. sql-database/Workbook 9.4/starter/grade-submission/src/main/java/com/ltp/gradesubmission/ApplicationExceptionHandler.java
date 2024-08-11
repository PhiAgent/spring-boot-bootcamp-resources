package com.ltp.gradesubmission;

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

import com.ltp.gradesubmission.exception.CourseNotFoundException;
import com.ltp.gradesubmission.exception.ErrorResponse;
import com.ltp.gradesubmission.exception.GradeNotFoundException;
import com.ltp.gradesubmission.exception.StudentNotFoundException;



@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CourseNotFoundException.class, GradeNotFoundException.class, StudentNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(RuntimeException ex){

        return new ResponseEntity(new ErrorResponse(List.of(ex.getMessage())), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoScoreException.class)
    public ResponseEntity<Object> handleNoScoreException(NoScoreException NoScoreException) {

        ErrorResponse errorResponse = new ErrorResponse(NoScoreException.getMessage());

        return new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoScoreException.class)
    public ResponseEntity<Object> handleNoScoreException(NoScoreException NoScoreException) {

        ErrorResponse errorResponse = new ErrorResponse(NoScoreException.getMessage());

        return new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
    }

    @Override //identical to method from workbook 8.3
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorMessages = new ArrayList<String>();
        for(ObjectError error: ex.getBindingResult().getAllErrors()) {
            errorMessages.add(error.getDefaultMessage());
        }
        return new ResponseEntity<>(new ErrorResponse(errorMessages), HttpStatus.BAD_REQUEST);
    }

}

package com.Quess.FinalProject.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotfoundException(ResourceNotFoundException exception){
        ErrordDetails errordDetails= new ErrordDetails(exception.getMessage());
        return new ResponseEntity(errordDetails,HttpStatus.NOT_FOUND);
    }
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> handleResourceNotfoundException(Exception exception){
//        ErrordDetails errordDetails= new ErrordDetails(exception.getMessage());
//        return new ResponseEntity(errordDetails,HttpStatus.NOT_FOUND);}

}

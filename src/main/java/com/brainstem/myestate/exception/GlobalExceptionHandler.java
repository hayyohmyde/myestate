package com.brainstem.myestate.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private Object[] fieldName;

    //handle specific exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<CustomErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            WebRequest webRequest){
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                new Date(), ex.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
    }
    //global exceptions
    @ExceptionHandler(Exception.class)
    private ResponseEntity<CustomErrorResponse> handleGlobalException(
            Exception ex,
            WebRequest webRequest){
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                new Date(), ex.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //handling validation error
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}

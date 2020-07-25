package com.proj.donemcd.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class StudentExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
        Response response = new Response();
        response.setErrorCode(500);
        response.setMessage(ex.getMessage());
        return buildResponseEntity(response);
    }

    private ResponseEntity<Object> buildResponseEntity(Response response){
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getErrorCode()));
    }
}

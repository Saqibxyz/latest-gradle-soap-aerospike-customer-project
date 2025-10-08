package com.rest.demo.exceptions;

import com.sun.xml.ws.fault.ServerSOAPFaultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {
    @ExceptionHandler(ServerSOAPFaultException.class)
    public ResponseEntity<String> serverSOAPFaultException(ServerSOAPFaultException ex) {
        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception)
    {
        return new ResponseEntity<>("Something went wrong"+exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

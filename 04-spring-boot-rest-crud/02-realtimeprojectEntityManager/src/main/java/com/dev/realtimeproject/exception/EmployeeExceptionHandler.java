package com.dev.realtimeproject.exception;

import com.dev.realtimeproject.controller.EmployeeErrorResponse;
import com.dev.realtimeproject.entity.Employee;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class EmployeeExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(EmployeeNotFoundException exc){
        String status = String.valueOf(HttpStatus.NOT_FOUND.value());
        String message = exc.getMessage();
        return new ResponseEntity<>(new EmployeeErrorResponse(status,message, LocalDateTime.now()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse>handleException(Exception exc){
        String status = String.valueOf(HttpStatus.BAD_REQUEST.value());
        String message = exc.getMessage();
        return new ResponseEntity<>(new EmployeeErrorResponse(status,message,LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }



}

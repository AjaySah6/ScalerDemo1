package com.dev_ajay.scalerdemo1.advice;

import com.dev_ajay.scalerdemo1.DTO.ErrorDTO;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Global Exception Handler
// This will be called when any exception is thrown by any controller

// designed to handle NullPointerException exceptions and return a custom error message.
//@ControllerAdvice: Indicates that this class provides centralized exception handling across all controllers.
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDTO> handleNullPointerException() {

        ErrorDTO errorDto = new ErrorDTO();

        errorDto.setMessage("Something went wrong. Please try again");

        return new ResponseEntity<>(errorDto, HttpStatusCode.valueOf(404));

    }
}
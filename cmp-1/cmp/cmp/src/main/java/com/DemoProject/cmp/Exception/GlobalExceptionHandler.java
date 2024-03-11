package com.DemoProject.cmp.Exception;

import com.DemoProject.cmp.Exception.ErrorResponse;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler  extends RuntimeException {
    @ExceptionHandler(value = { CustomerNotFoundException.class })
    public ResponseEntity<Object> handleApiRequestException(CustomerNotFoundException ex) {
        ErrorResponse apiException = new ErrorResponse(ex.getMessage(), ex.getErrorDetails(), ex.getErrorDetails(), ex.getHttp(), ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = { TodoException.class })
    public ResponseEntity<Object> handleApiRequestException(TodoException ex) {
       ErrorResponse apiException = new ErrorResponse(ex.getMessage(), ex.ErrorDetails,
                "For Help please visit https://www.help.com", ex.http, ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }



    }


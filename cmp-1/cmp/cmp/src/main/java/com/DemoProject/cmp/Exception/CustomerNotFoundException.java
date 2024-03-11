package com.DemoProject.cmp.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@Data

public class CustomerNotFoundException extends RuntimeException {
     private String ErrorDetails;
     private  HttpStatus http;



    public CustomerNotFoundException(String msg, String errorDetails, HttpStatus http) {
        super(msg);
        this.ErrorDetails = errorDetails;
        this.http = http;
    }

    public CustomerNotFoundException(String msg, Throwable cause) {

        super(msg, cause);
    }
    @Override
    public StackTraceElement[] getStackTrace() {
        return new StackTraceElement[0];
    }
}

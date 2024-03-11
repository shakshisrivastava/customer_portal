package com.DemoProject.cmp.Exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data

public class TodoException extends RuntimeException {
    String ErrorDetails;
    HttpStatus http;

    public TodoException(String msg, String errorDetails, HttpStatus http) {
        super(msg);
        this.ErrorDetails = errorDetails;
        this.http = http;
    }

    public TodoException(String msg, Throwable cause)
    {
        super(msg, cause);
    }


}



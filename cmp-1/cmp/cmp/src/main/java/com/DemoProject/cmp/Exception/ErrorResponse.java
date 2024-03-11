package com.DemoProject.cmp.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private String message;
    private HttpStatus httpStatus;
    private ZonedDateTime zonedDateTime;
    private String ErrorDetails;
    private String support;

    public ErrorResponse(String message, String ErrorDetails, String support, HttpStatus httpStatus,
                        ZonedDateTime zonedDateTime) {
        this.message = message;
        this.ErrorDetails = ErrorDetails;
        this.support = support;
        this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;
    }

}


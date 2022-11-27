package com.brainstem.myestate.payload;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
    private String statusCode;

    public ErrorDetails(Date timestamp, String message, String statusCode) {
        this.timestamp = new Date();
        this.message = message;
        this.statusCode = statusCode;
    }

    public ErrorDetails(String message) {
        this.message = message;
    }

    public ErrorDetails(String message, String statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}


package com.brainstem.myestate.exception;

import java.util.Date;

public class CustomErrorResponse {
    private final Date date;
    private final String message;
    private final String Details;

    public CustomErrorResponse(Date date, String message, String details) {
        this.date = date;
        this.message = message;
        Details = details;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return Details;
    }

    @Override
    public String toString() {
        return "CustomErrorResponse{" +
                "date=" + date +
                ", message='" + message + '\'' +
                ", Details='" + Details + '\'' +
                '}';
    }
}

package com.brainstem.myestate.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class ResponseObject {
    private Date date = new Date();
    private Object body;
    private String  message;

    public ResponseObject(Object body, String message) {
        this.body = body;
        this.message = message;
    }
}

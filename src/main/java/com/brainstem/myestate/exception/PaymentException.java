package com.brainstem.myestate.exception;

import java.time.LocalDateTime;

public class PaymentException extends RuntimeException{
    public PaymentException(String message){
        super(message);
    }

}

package com.example.Foods.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
public class GateWayTimeOut extends RuntimeException{

    public GateWayTimeOut(String message){
        super(message);
    }
}

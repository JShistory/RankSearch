package com.example.Foods.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
public class RateLimitExceeded extends RuntimeException{

    public RateLimitExceeded(String message){
        super(message);
    }
}

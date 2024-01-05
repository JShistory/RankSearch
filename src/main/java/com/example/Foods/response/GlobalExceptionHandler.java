package com.example.Foods.response;

import com.example.Foods.exception.ForbiddenException;
import com.example.Foods.exception.GateWayTimeOut;
import com.example.Foods.exception.RateLimitExceeded;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import java.io.IOException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.Forbidden;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RateLimitExceeded.class)
    @ResponseStatus(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
    public ResponseEntity<BasicResponse> TimeOut(RateLimitExceeded e){
        BasicResponse basicResponse = new BasicResponse();
        ErrorCode errorCode = ErrorCode.fromHttpStatsCode(429);
        basicResponse = BasicResponse.builder()
                .message(errorCode.getMessage())
                .httpStatus(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
                .code(errorCode.getHttpStatusCode())
                .build();
        return new ResponseEntity<>(basicResponse,basicResponse.getHttpStatus());
    }


    @ExceptionHandler(value = GateWayTimeOut.class)
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    public ResponseEntity<BasicResponse> TimeOut(GateWayTimeOut e){
        BasicResponse basicResponse = new BasicResponse();
        ErrorCode errorCode = ErrorCode.fromHttpStatsCode(504);
        basicResponse = BasicResponse.builder()
                .message(errorCode.getMessage())
                .httpStatus(HttpStatus.GATEWAY_TIMEOUT)
                .code(errorCode.getHttpStatusCode())
                .build();
        return new ResponseEntity<>(basicResponse,basicResponse.getHttpStatus());
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<BasicResponse> NotFoundHandler(NotFoundException e){
        BasicResponse basicResponse = new BasicResponse();
        ErrorCode errorCode = ErrorCode.fromHttpStatsCode(404);
        basicResponse = BasicResponse.builder()
                .message(errorCode.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .code(errorCode.getHttpStatusCode())
                .build();
        return new ResponseEntity<>(basicResponse,basicResponse.getHttpStatus());
    }
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = ForbiddenException.class)
    public ResponseEntity<BasicResponse> ForbiddenHandler(ForbiddenException e){
        BasicResponse basicResponse = new BasicResponse();
        ErrorCode errorCode = ErrorCode.fromHttpStatsCode(403);
        basicResponse = BasicResponse.builder()
                .message(errorCode.getMessage())
                .httpStatus(HttpStatus.FORBIDDEN)
                .code(errorCode.getHttpStatusCode())
                .build();
        return new ResponseEntity<>(basicResponse,basicResponse.getHttpStatus());
    }
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    @ExceptionHandler(value = UnrecognizedPropertyException.class)
//    public ResponseEntity<BasicResponse> UnrecognizedPropertyExceptionHandler(UnrecognizedPropertyException e){
//        BasicResponse basicResponse = new BasicResponse();
//        ErrorCode errorCode = ErrorCode.fromHttpStatsCode(404);
//        basicResponse = BasicResponse.builder()
//                .message(errorCode.getMessage())
//                .httpStatus(HttpStatus.NOT_FOUND)
//                .code(errorCode.getHttpStatusCode())
//                .build();
//        return new ResponseEntity<>(basicResponse,basicResponse.getHttpStatus());
//    }

//    @ExceptionHandler(value = IOException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public ResponseEntity<BasicResponse> IOExceptionHandler(IOException e){
//        BasicResponse basicResponse = new BasicResponse();
//        ErrorCode errorCode = ErrorCode.fromHttpStatsCode(404);
//        basicResponse = BasicResponse.builder()
//                .message(errorCode.getMessage())
//                .httpStatus(HttpStatus.NOT_FOUND)
//                .code(errorCode.getHttpStatusCode())
//                .build();
//        return new ResponseEntity<>(basicResponse,basicResponse.getHttpStatus());
//    }

}

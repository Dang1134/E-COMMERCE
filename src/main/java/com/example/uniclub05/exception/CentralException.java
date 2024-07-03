package com.example.uniclub05.exception;

import com.example.uniclub05.payload.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralException {
    @ExceptionHandler ({RuntimeException.class , InsertProductException.class , SaveFileException.class})

    public ResponseEntity <?> handleException(Exception e){
        BaseResponse response = new BaseResponse();
        response.setStatusCode(500);
        response.setData("");
        response.setMessage(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

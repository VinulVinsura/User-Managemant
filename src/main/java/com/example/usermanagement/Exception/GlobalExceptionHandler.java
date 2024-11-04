package com.example.usermanagement.Exception;

import com.example.usermanagement.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseDto> authenticationExceptionHandle(AuthenticationException ex, WebRequest request){
        return ResponseEntity.ok(new ResponseDto("03",
                "Invalid Credentials",
                null));

    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseDto> badRequestExceptionHandle(BadRequestException ex){
        return ResponseEntity.ok(new ResponseDto("06","Bad Request",null));
    }
}


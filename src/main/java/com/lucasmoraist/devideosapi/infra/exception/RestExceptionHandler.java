package com.lucasmoraist.devideosapi.infra.exception;

import com.lucasmoraist.devideosapi.exception.ExceptionDTO;
import com.lucasmoraist.devideosapi.exception.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    protected ResponseEntity<ExceptionDTO> resourceNotFoundException(ResourceNotFound e){
        ExceptionDTO dto = new ExceptionDTO(e.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.badRequest().body(dto);
    }

}

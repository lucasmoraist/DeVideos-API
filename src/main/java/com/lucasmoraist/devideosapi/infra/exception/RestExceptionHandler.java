package com.lucasmoraist.devideosapi.infra.exception;

import com.lucasmoraist.devideosapi.exception.ExceptionDTO;
import com.lucasmoraist.devideosapi.exception.ResourceNotFound;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
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

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ExceptionDTO> illegalArgument(IllegalArgumentException e){
        ExceptionDTO dto = new ExceptionDTO(e.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<ExceptionDTO> nullPointerExceptions(DataIntegrityViolationException e){
        ExceptionDTO dto = new ExceptionDTO("Invalid Values", HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionDTO> genericExceptions(Exception e){
        ExceptionDTO dto = new ExceptionDTO(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(DataAccessException.class)
    protected ResponseEntity<ExceptionDTO> failedConnectDatabase(DataAccessException e){
        ExceptionDTO dto = new ExceptionDTO("Failed to connect to database", HttpStatus.BAD_GATEWAY);
        return ResponseEntity.badRequest().body(dto);
    }
}
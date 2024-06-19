package com.lucasmoraist.devideosapi.exception;

import org.springframework.http.HttpStatus;

public record ExceptionDTO(String msg, HttpStatus status) {
}

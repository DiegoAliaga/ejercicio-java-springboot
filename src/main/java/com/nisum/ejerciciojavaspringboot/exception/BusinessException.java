package com.nisum.ejerciciojavaspringboot.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private final HttpStatus httpStatus;

    public BusinessException(
            String mensaje,
            HttpStatus httpStatus) {

        super(mensaje);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

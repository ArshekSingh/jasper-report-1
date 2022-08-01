package com.example.jasperreport1.exception;

import org.springframework.http.HttpStatus;


public class ObjectNotFoundException extends  Exception {
    private HttpStatus status;
    private Integer code;
    private String message;
    private Object responseObject;

    public ObjectNotFoundException(String message, HttpStatus status,  Object responseObject) {
        this.status = status;
        this.code = status.value();
        this.message = message;
        this.responseObject = responseObject;
    }
    public ObjectNotFoundException(String message, HttpStatus status) {
        this.status = status;
        this.code = status.value();
        this.message = message;
    }


}

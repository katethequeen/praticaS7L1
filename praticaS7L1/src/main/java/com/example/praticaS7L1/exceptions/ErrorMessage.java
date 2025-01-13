package com.example.praticaS7L1.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorMessage {
    private String message;
    private HttpStatus statusCode;

    public ErrorMessage() {

    }

    public ErrorMessage(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}

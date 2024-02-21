package com.example.demo.Exception;

public class UserInputNotValidException extends RuntimeException {
    public UserInputNotValidException(String message) {
        super(message);
    }
}
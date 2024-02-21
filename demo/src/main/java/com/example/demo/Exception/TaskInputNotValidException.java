package com.example.demo.Exception;

public class TaskInputNotValidException extends RuntimeException {
    public TaskInputNotValidException(String message) {
        super(message);
    }
}

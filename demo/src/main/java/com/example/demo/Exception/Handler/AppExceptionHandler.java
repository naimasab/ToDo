package com.example.demo.Exception.Handler;

import com.example.demo.Exception.EntityNotFoundException;
import com.example.demo.Exception.ErrorMessage;
import com.example.demo.Exception.TaskInputNotValidException;
import com.example.demo.Exception.UserInputNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Date;
@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> entityNotFound (EntityNotFoundException e){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(e.getMessage()).code(404)
                .dateTime((Date) new Date())
                .build();
                return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {UserInputNotValidException.class, TaskInputNotValidException.class})
    public ResponseEntity<Object> entityInputError (Exception e){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(e.getMessage()).code(400)
                .dateTime((Date) new Date())
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }


}

package com.api.chat.handler;

import com.api.chat.execeptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNotfoundException.class)
    public ResponseEntity<String> userNotFound(UserNotfoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(NotAuthorizedUser.class)
    public ResponseEntity<String> notAuthorizedUser(NotAuthorizedUser ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyException.class)
    public ResponseEntity<String> userFound(UserAlreadyException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(PasswordErrorException.class)
    public ResponseEntity<String> passworError(PasswordErrorException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<String> invalidToken(InvalidTokenException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }
}

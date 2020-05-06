package com.gomokumanager.GomokuManager.exceptionManager;

import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Class which treats runtime exception using RestControllerAdvice
 */

@RestControllerAdvice
public class ExceptionManager {
    @ExceptionHandler(RuntimeException.class)
    public String handleException(){
        return "Error occured: runtime exception.\n";
    }
}

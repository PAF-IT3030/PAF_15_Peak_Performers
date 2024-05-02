package com.spring.social_media_application.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger logg = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        logg.error("Exception : {}", ex.getMessage());
        List<String> details = new ArrayList<>();
        details.add("Other exceptions");
        details.add(ex.getMessage());
        ApiErrors errors = new ApiErrors(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), details);
        return ResponseEntity.status(errors.getStatus()).body(errors);
    }

    /**
     * This method will Handle object reference not found exception in mapper layer
     *
     * @return this method advise controller for reference not found exception
     */
    @ExceptionHandler(ReferenceNotFoundException.class)
    public ResponseEntity<Object> handleException(ReferenceNotFoundException ex) {
        logg.error("Exception : {}", ex.getMessage());
        List<String> details = new ArrayList<>();
        details.add("Object conversion failed in mapper layer");
        details.add(ex.getMessage());
        ApiErrors errors = new ApiErrors(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), details);
        return ResponseEntity.status(errors.getStatus()).body(errors);
    }
}

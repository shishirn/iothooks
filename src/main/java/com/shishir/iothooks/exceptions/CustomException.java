package com.shishir.iothooks.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.ResponseEntity.notFound;

@RestControllerAdvice
@Slf4j
public class CustomException {

    @ExceptionHandler(value = {SensorNotFoundException.class})
    public ResponseEntity vehicleNotFound(SensorNotFoundException ex, WebRequest request) {
        log.debug("Sensor not found");
        return notFound().build();
    }
}

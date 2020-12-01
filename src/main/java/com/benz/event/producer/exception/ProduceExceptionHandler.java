package com.benz.event.producer.exception;


import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.benz.event.producer.constants.EventProduceConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class ProduceExceptionHandler.
 */
@RestControllerAdvice

/** The Constant log. */
@Slf4j
public class ProduceExceptionHandler{
	
    /**
     * Handle access denied exception.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException exception) {
        String exceptionMessage = exception.getMessage();
        log.error("Access Denied", exception);
        String traceId =  MDC.get(EventProduceConstants.TRACE_ID);
        LogRefClass logref = LogRefClass.builder().logRef(traceId).status(401)
                .message(exceptionMessage).build();
        return new ResponseEntity<>(logref, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handle validation exception.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(ProducerServiceException.class)
    protected ResponseEntity<Object> handleValidationException(ProducerServiceException exception) {
        String correlationId = MDC.get(EventProduceConstants.TRACE_ID);
        log.error("Internal Service Error", exception);
        LogRefClass logref = LogRefClass.builder().logRef(correlationId).status(500).message(exception.getMessage())
                .build();
        return new ResponseEntity<>(logref, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

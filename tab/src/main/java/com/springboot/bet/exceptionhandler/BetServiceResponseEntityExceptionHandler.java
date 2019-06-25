package com.springboot.bet.exceptionhandler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.springboot.bet.exception.ErrorDetails;
import com.springboot.bet.exception.InvalidBetTypeException;
import com.springboot.bet.exception.InvalidDateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Exception handler for Rest Exception
 */
@ControllerAdvice
public class BetServiceResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle all kinds of Exception (if ExceptionHandler is not defined)
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), Arrays.asList(ex.getMessage()),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle invalid Json parameter Exception
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({InvalidBetTypeException.class, InvalidDateException.class})
    public final ResponseEntity<Object> handleInvalidJsonException(RuntimeException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), Arrays.asList(ex.getMessage()),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle All http message not readable Exception
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable cause = ex.getCause();
        if (cause instanceof JsonMappingException) {
            if (getIsInvalidJsonException(cause.getCause())) {
                return handleInvalidJsonException((RuntimeException) cause.getCause(), request);
            }
        }
        ErrorDetails errorDetails = new ErrorDetails(new Date(), Arrays.asList(ex.getMessage()),
                request.getDescription(false));
        return handleExceptionInternal(ex, errorDetails, headers, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Handle all invalid parameter exception - This validation only happen after the bean is created
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        ErrorDetails errorDetails = new ErrorDetails(new Date(), errors, request.getDescription(false));
        return handleExceptionInternal(
                ex, errorDetails, headers, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Check if it is an invalid Json exception or not
     *
     * @param cause
     * @return
     */
    private boolean getIsInvalidJsonException(Throwable cause) {
        if ((cause instanceof InvalidDateException) || (cause instanceof InvalidBetTypeException)) {
            return true;
        }
        return false;
    }
}

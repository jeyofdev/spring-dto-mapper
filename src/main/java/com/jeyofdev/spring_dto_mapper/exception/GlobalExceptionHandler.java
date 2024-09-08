package com.jeyofdev.spring_dto_mapper.exception;

import com.jeyofdev.spring_dto_mapper.util.Helper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // when entity is not found
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException exception, HttpServletRequest request) {
        return handleException(exception, HttpStatus.NOT_FOUND, request);
    }

    // when data constraint validation failed
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException exception) {
        Map<String, String> errors = new HashMap<>();

        // format error message
        exception.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        });

        errors.put("validate", String.valueOf(exception.getConstraintViolations().isEmpty()));

        return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // when errors occur when reading,
    // writing, or accessing files, networks, data streams,
    // or other input/output resources
    @ExceptionHandler(IOException.class)
    public ResponseEntity<Map<String, String>> handleIOException(IOException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Invalid format", exception.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // when trying to access an object that does not exist
    // missing or malformed
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();

        // Check if the error is for a missing request body
        if (exception.getMessage() != null && exception.getMessage().contains("Required request body is missing")) {
            errors.put("error", "Request body is missing or malformed");
        } else {
            errors.put("error", "Malformed JSON request: " + exception.getMessage());
        }

        return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // when trying to access an object that is not initialized
    // and value is null
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Map<String, String>> handleNullPointerException(NullPointerException exception, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // when invalid or inappropriate argument.
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception, HttpServletRequest request) {
        return handleException(exception, HttpStatus.CONFLICT, request);
    }

    // others
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception, HttpServletRequest request) {
        return handleException(exception, HttpStatus.BAD_REQUEST, request);
    }

    private ResponseEntity<ErrorResponse> handleException(Exception exception, HttpStatus status, HttpServletRequest request) {
        exception.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setMessage(exception.getMessage());
        errorResponse.setStatus(status.value());
        errorResponse.setDate(Helper.simpleDateFormat());
        errorResponse.setPath(request.getRequestURI());

        return new ResponseEntity<>(errorResponse, status);
    }
}

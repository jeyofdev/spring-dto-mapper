package com.jeyofdev.spring_dto_mapper.exception;

import com.jeyofdev.spring_dto_mapper.util.Helper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException exception, HttpServletRequest request) {
        return handleException(exception, HttpStatus.NOT_FOUND, request);
    }

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

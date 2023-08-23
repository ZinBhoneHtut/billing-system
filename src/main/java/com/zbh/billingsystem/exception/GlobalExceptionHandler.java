package com.zbh.billingsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        ErrorResponse errorDetail = new ErrorResponse(
                new Date(),
                HttpStatus.NOT_FOUND.toString(),
                exception.getMessage(),
                "",
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> badRequestException(BadRequestException exception, WebRequest request) {
        ErrorResponse errorDetail = new ErrorResponse(
                new Date(),
                exception.getStatus().toString(),
                exception.getMessage(),
                "",
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetail, exception.getStatus());
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParameterException(MissingServletRequestParameterException exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(
                new Date(),
                HttpStatus.BAD_REQUEST.name(),
                exception.getMessage(),
                "",
                request.getDescription(false)
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException exception, WebRequest request) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<String> errorMessages = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            errorMessages.add(fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(new ErrorResponse(
                new Date(),
                HttpStatus.BAD_REQUEST.name(),
                String.join(", ", errorMessages),
                "Validation error",
                request.getDescription(false)
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(
                new Date(),
                HttpStatus.UNAUTHORIZED.name(),
                "Invalid username or password",
                exception.getMessage(),
                request.getDescription(false)
        ), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(
                new Date(),
                HttpStatus.BAD_REQUEST.name(),
                "Your request data is missing or invalid",
                "",
                request.getDescription(false)
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(SQLIntegrityConstraintViolationException ex, WebRequest request) {
        String errorMessage = extractConstraintDetails(ex);
        return new ResponseEntity<>(new ErrorResponse(
                new Date(),
                HttpStatus.BAD_REQUEST.name(),
                errorMessage,
                "Duplicate",
                request.getDescription(false)
        ), HttpStatus.BAD_REQUEST);
    }

    private String extractConstraintDetails(SQLIntegrityConstraintViolationException ex) {
        String message = ex.getMessage();
        int startIndex = message.indexOf("Duplicate entry");
        if (startIndex >= 0) {
            startIndex += "Duplicate entry '".length();
            int endIndex = message.indexOf("'", startIndex);
            if (endIndex > startIndex) {
                return message.substring(startIndex, endIndex) + " is already exists.";
            }
        }
        return "Unknown constraint violation";
    }

}

package com.longld.commerceflow.exception;

import com.longld.commerceflow.common.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Object>> handleAppException(AppException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        log.warn("AppException occurred: code={}, message={}", errorCode.getCode(), ex.getMessage());

        ApiResponse<Object> response = ApiResponse.error(errorCode.getCode(), ex.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        log.warn("Validation error: {}", errors);

        ApiResponse<Map<String, String>> response = ApiResponse.<Map<String, String>>builder()
                .code(ErrorCode.VALIDATION_ERROR.getCode())
                .message(ErrorCode.VALIDATION_ERROR.getMessage())
                .data(errors)
                .build();

        return ResponseEntity.status(ErrorCode.VALIDATION_ERROR.getHttpStatusCode()).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation ->
                errors.put(violation.getPropertyPath().toString(), violation.getMessage()));

        log.warn("Constraint violation: {}", errors);

        ApiResponse<Map<String, String>> response = ApiResponse.<Map<String, String>>builder()
                .code(ErrorCode.VALIDATION_ERROR.getCode())
                .message(ErrorCode.VALIDATION_ERROR.getMessage())
                .data(errors)
                .build();

        return ResponseEntity.status(ErrorCode.VALIDATION_ERROR.getHttpStatusCode()).body(response);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNoResourceFoundException(NoResourceFoundException ex) {
        log.warn("Resource not found: {}", ex.getMessage());
        ApiResponse<Object> response = ApiResponse.error(
                ErrorCode.RESOURCE_NOT_FOUND.getCode(),
                "Endpoint or resource not found: " + ex.getResourcePath()
        );
        return ResponseEntity.status(ErrorCode.RESOURCE_NOT_FOUND.getHttpStatusCode()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneralException(Exception ex) {
        log.error("Unhandled Exception caught: ", ex);

        ApiResponse<Object> response = ApiResponse.error(
                ErrorCode.UNCATEGORIZED_EXCEPTION.getCode(),
                ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage()
        );
        return ResponseEntity.status(ErrorCode.UNCATEGORIZED_EXCEPTION.getHttpStatusCode()).body(response);
    }
}

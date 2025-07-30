package org.example.test.shared.presentation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
        );

        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("message", "入力値に不正があります");
        errorResponse.put("errors", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(fieldName, message);
        });

        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("message", "バリデーションエラーが発生しました");
        errorResponse.put("errors", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleInternalServerError(Exception ex) {
        logger.error(ex.getMessage(), ex);

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.put("message", "サーバー内部エラーが発生しました");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}

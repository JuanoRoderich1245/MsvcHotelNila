package org.grupouno.msvcnegocio.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.grupouno.msvcempleado.exception.EntityNotFoundException;
import org.grupouno.msvcempleado.exception.IllegalOperationException;
import org.grupouno.msvcempleado.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalOperationException.class)
    public ResponseEntity<Object> handleIllegalOperationException(IllegalOperationException ex) {
        ApiResponse<Object> response = new ApiResponse<>(false,
                "Error interno del servidor",
                null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> globalExceptionHandler(Exception ex, WebRequest request){
        ApiResponse<Object> response = new ApiResponse<>(false,
                ex.getMessage(),
                null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> resourceNotFoundException(EntityNotFoundException ex, WebRequest request){
        ApiResponse<Object> response = new ApiResponse<>(false,
                "Error interno del servidor",
                null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errores = new HashMap<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errores.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        ApiResponse<Object> response = new ApiResponse<>(false, "Error de validación", errores);
        return ResponseEntity.badRequest().body(response);
    }
}

package dev.reso.recipeit.infra.controller.handler;

import dev.reso.recipeit.core.exceptions.InvalidArgumentException;
import dev.reso.recipeit.core.exceptions.RecipeHasRelatedDataException;
import dev.reso.recipeit.infra.persistence.DataBaseException;
import dev.reso.recipeit.core.exceptions.DuplicateRecipeKeyException;
import dev.reso.recipeit.core.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateRecipeKeyException.class)
    public ResponseEntity<StandardError> handleRecipeAlreadyExistsException(DuplicateRecipeKeyException ex, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.CONFLICT.value());
        err.setError("duplicate key");
        err.setMessage(ex.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("resource not found");
        err.setMessage(ex.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(RecipeHasRelatedDataException.class)
    public ResponseEntity<StandardError> handleRecipeHasRelatedDataException(RecipeHasRelatedDataException ex, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.CONFLICT.value());
        err.setError("conflict error");
        err.setMessage(ex.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<StandardError> handleInvalidArgumentException(InvalidArgumentException ex, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("Invalid argument");
        err.setMessage(ex.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandardError> handleDataBaseException(DataBaseException ex, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("database error");
        err.setMessage(ex.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}

package com.bytescolab.demo.featureflagapi.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorApi> handleError(Exception exception){
        ErrorApi error = buildError(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorApi> handleEntityNotFoundException(Exception exception){
        ErrorApi error = buildError(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorApi> handleValidationException(MethodArgumentNotValidException ex){
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        //TODO: Si hay mas de un error en la validación devuelve el array de mensajes de error
        ErrorApi err = buildError(errorMessages.toString(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorApi> handleBadCredentials(BadCredentialsException ex) {
        ErrorApi error = buildError("Credenciales inválidas. Por favor, verifique el usuario y la contraseña.", HttpStatus.FORBIDDEN);
        return ResponseEntity.status(error.getStatus()).body(error);
    }


    private ErrorApi buildError(String message, HttpStatus status) {
        return ErrorApi.builder()
                .timestamp(String.valueOf(Timestamp.from(ZonedDateTime.now().toInstant())))
                .error(status.getReasonPhrase())
                .status(status.value())
                .message(message)
                .build();
    }
}

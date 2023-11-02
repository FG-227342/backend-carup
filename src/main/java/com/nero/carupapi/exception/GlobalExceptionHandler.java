package com.nero.carupapi.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    // Manejar una excepción específica y proporcionar un mensaje personalizado
    @ExceptionHandler(MyException.class)
    public ResponseEntity<String> handleCustomException(MyException ex) {
        String mensaje = "Ocurrió una excepción personalizada: " + ex.getMessage();
        return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
    }

    // Manejar una excepción genérica y proporcionar un mensaje personalizado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        String mensaje = "Ocurrió una excepción: " + ex.getMessage();
        return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDatabaseException(DataAccessException ex) {
        // Puedes personalizar el mensaje y el código de estado según tus necesidades
        String mensaje = "Error en la base de datos: " + ex.getMessage();
        return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDatabaseException(DataIntegrityViolationException ex) {
        String mensaje = "Error de BD:" + ex.getRootCause();
        return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

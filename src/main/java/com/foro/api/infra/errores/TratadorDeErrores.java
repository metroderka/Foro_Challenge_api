package com.foro.api.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404() {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity tratarError400(DataIntegrityViolationException e) {
        var errores = e.getMessage();
        return ResponseEntity.badRequest().body(errores);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e) {
    //    var errores = e.getFieldError();
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
        public ResponseEntity tratarError400(InvalidDataAccessApiUsageException e){
        var errores = e.getMessage();
        return ResponseEntity.badRequest().body(errores);
    }
    private record DatosErrorValidacion (String campo , String error){
        public DatosErrorValidacion (FieldError error){
            this(error.getField(),error.getDefaultMessage());
        }
    }
}
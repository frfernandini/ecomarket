package com.fernandini.msvc.productos.exceptions;

import com.fernandini.msvc.productos.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandlerException {
    private ErrorDTO createErrorDTO(int status, Date date, Map<String,String> errorMap){
        ErrorDTO errorDTO = new ErrorDTO();

        errorDTO.setStatus(status);
        errorDTO.setErrors(errorMap);
        errorDTO.setDate(date);

        return errorDTO;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationFields(MethodArgumentNotValidException exception){
        Map<String,String> errorMap = new HashMap<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()){
            errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(this.createErrorDTO(HttpStatus.BAD_REQUEST.value(),new Date(),errorMap));
    }
    @ExceptionHandler(ProductoException.class)
    public ResponseEntity<ErrorDTO> handleProductoException(ProductoException exception){
        if(exception.getMessage().contains("no esta registrado")){
            Map<String,String> errorMap = Collections.singletonMap("Producto no encontrado",exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(this.createErrorDTO(HttpStatus.NOT_FOUND.value(),new Date(),errorMap));
        }else{
            Map<String,String> errorMap = Collections.singletonMap("Producto encontrado",exception.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(this.createErrorDTO(HttpStatus.CONFLICT.value(),new Date(),errorMap));
        }
    }
}

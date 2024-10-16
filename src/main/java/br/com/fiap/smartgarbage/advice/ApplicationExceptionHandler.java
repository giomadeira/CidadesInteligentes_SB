package br.com.fiap.smartgarbage.advice;

import br.com.fiap.smartgarbage.exception.NotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handlerInvalidArguments(MethodArgumentNotValidException error) {
        Map<String, String> errorMap = new HashMap<>();
        List<FieldError> fields = error.getBindingResult().getFieldErrors();
        for(FieldError field : fields){
            errorMap.put(field.getField(), field.getDefaultMessage());
        }
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Map<String, String> handlerRuntimeException(NotFoundException error) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", error.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, String> error = new HashMap<>();
        Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException) {
            InvalidFormatException invalidFormatException = (InvalidFormatException) cause;
            String targetType = invalidFormatException.getTargetType().getSimpleName();
            if (targetType.equals("CollectTypeEnum")) {
                error.put("message", "Tipo de coleta inválido, são aceitos: [NOT_RECYCLABLE, DANGEROUS, ORGANIC, RECYCLABLE]");
            } else {
                error.put("message", "Invalid value provided.");
            }
        } else {
            error.put("message", "Invalid request payload.");
        }
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> handleIntegrityViolation(){

        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("erro", "Usuário já cadastrado!");

        return errorMap;

    }
}

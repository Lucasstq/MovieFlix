package br.com.movieflix.config;

import br.com.movieflix.exception.UsernameOrPasswordInvalid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        if (ex.getMessage() != null && ex.getMessage().contains("email")) {
            return new ResponseEntity<>("O e-mail fornecido já está em uso.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Erro de integridade de dados. Por favor, verifique os dados enviados.", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UsernameOrPasswordInvalid.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNotFoundException(UsernameOrPasswordInvalid ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleArgumentsNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->
            errors.put(((FieldError) error).getField(), error.getDefaultMessage())
            );
        return errors;
    }
}

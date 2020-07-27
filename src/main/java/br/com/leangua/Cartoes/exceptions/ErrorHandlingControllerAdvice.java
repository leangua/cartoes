package br.com.leangua.Cartoes.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {
  
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
  @ResponseBody
  Map<String, String> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    List<FieldError> errors = e.getBindingResult().getFieldErrors();
    Map<String, String> body = new HashMap<>();
    
    for(FieldError error : errors) {
      body.put(error.getField(), error.getDefaultMessage());
    }
    
    return body;
  }
}


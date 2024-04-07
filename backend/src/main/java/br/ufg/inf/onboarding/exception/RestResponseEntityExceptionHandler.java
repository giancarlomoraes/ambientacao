package br.ufg.inf.onboarding.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> result = new HashMap<>();
        result.put("codigo", Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage());
        result.put("descricao", "");
        result.put("status", status.value());


        return new ResponseEntity(result, status);
    }
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> EducacaoCidadaExceptionHandle(
            CustomException e, WebRequest request)  {
        Map<String, Object> result = new HashMap<>();
        result.put("codigo", e.getCodigo());
        result.put("descricao", e.getDescricao());
        result.put("status", e.getStatus().value());
        if(!Objects.isNull(e.getConteudo())){
            result.put("conteudo", e.getConteudo());
        }

        return new ResponseEntity(result, e.getStatus());
    }


}

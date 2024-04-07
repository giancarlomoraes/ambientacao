package br.ufg.inf.onboarding.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomException extends RuntimeException {
    private String codigo;
    private HttpStatus status;
    private String descricao;
    private Object conteudo;

    public CustomException(String codigo, HttpStatus status, String descricao){
        this.codigo = codigo;
        this.status = status;
        this.descricao = descricao;
    }

    public CustomException(HttpStatus status, String descricao){
        this.status = status;
        this.descricao = descricao;
    }

    public CustomException(String codigo, HttpStatus status, String descricao, Object conteudo){
        this.codigo = codigo;
        this.status = status;
        this.descricao = descricao;
        this.conteudo = conteudo;
    }

    public static CustomException badRequest(String codigo, String descricao){
        return new CustomException(codigo, HttpStatus.BAD_REQUEST, descricao);
    }

    public static CustomException badRequest(String descricao){
        return new CustomException(HttpStatus.BAD_REQUEST, descricao);
    }

    public static CustomException notFound(String descricao){
        return new CustomException(HttpStatus.NOT_FOUND, descricao);
    }

    public static CustomException conflict(String codigo, String descricao){
        return new CustomException(codigo, HttpStatus.CONFLICT, descricao);
    }

    public static CustomException unauthorized(String codigo, String descricao){
        return new CustomException(codigo, HttpStatus.UNAUTHORIZED, descricao);
    }
}
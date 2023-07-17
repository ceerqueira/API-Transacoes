package br.com.banco.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NomeNotFoundExpecion extends RuntimeException{
    public NomeNotFoundExpecion (String mensagem){
        super(mensagem);
    }
    
}

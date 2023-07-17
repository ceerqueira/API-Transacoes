package br.com.banco.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class PeriodoNotFoundExpecion extends RuntimeException{

    public PeriodoNotFoundExpecion(String mensagem){
        super(mensagem);
    }
}

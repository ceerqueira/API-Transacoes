package br.com.banco.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TranscacoesPorNomePeriodoEPorNomeOperadorNotFoundExpecion extends RuntimeException {
    public TranscacoesPorNomePeriodoEPorNomeOperadorNotFoundExpecion ( String mensagem){
        super(mensagem);
    }
    
}

package br.com.banco.exception;

public class NomeNotFoundExpecion extends RuntimeException{
    public NomeNotFoundExpecion (String mensagem){
        super(mensagem);
    }
    
}

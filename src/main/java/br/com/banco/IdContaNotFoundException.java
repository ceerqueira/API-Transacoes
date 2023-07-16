package br.com.banco.exception;

public class IdContaNotFoundException extends RuntimeException {

    public IdContaNotFoundException(String mensagem) {
        super(mensagem);
    }
    
}

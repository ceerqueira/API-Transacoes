package br.com.banco.exception;

public class PeriodoNotFoundExpecion extends RuntimeException{

    public PeriodoNotFoundExpecion(String mensagem){
        super(mensagem);
    }
}

package br.com.banco.service;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public interface TransferenciaService {
    
    ResponseEntity<?> buscarTransacoes(Long contaId, LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo, String nomeOperador);

    ResponseEntity<?> transacoesPorContaId(Long contaId);

    ResponseEntity<?> transacoesPorPeriodo(LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo);

    ResponseEntity<?> transacoesPorNomeOperador(String nomeOperador);

    ResponseEntity<?> todasTransacoes();

    ResponseEntity<?> transcacoesPorNomePeriodoEPorNomeOperador(String nomeOperador, LocalDateTime inicioPeriodo,
            LocalDateTime fimPeriodo);

}

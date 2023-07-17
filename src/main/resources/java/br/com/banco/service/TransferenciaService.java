package br.com.banco.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.banco.domain.Transferencia;

@Service
public interface TransferenciaService {
    
    List<Transferencia> buscarTransacoes(Long contaId, LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo, String nomeOperador);

    List<Transferencia> transacoesPorContaId(Long contaId);

    List<Transferencia> transacoesPorPeriodo(LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo);

    List<Transferencia> transacoesPorNomeOperador(String nomeOperador);

    List<Transferencia> todasTransacoes();

    List<Transferencia> transcacoesPorNomePeriodoEPorNomeOperador(String nomeOperador, LocalDateTime inicioPeriodo,
            LocalDateTime fimPeriodo);

}

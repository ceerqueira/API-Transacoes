package br.com.banco.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.banco.domain.Transferencia;

@Service
public interface TransferenciaService {

    List<Transferencia> TransacoesPorContaId(Long contaId);

    List<Transferencia> TransacoesPorPeriodo(LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo);

    List<Transferencia> TransacoesPorNomeOperador(String nomeOperador);

    List<Transferencia> TodasTransacoes();

    List<Transferencia> TranscacoesPorNomePeriodoEPorNomeOperador(String nomeOperador, LocalDateTime inicioPeriodo,
            LocalDateTime fimPeriodo);

    

}

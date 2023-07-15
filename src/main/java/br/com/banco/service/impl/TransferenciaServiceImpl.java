package br.com.banco.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.banco.domain.Transferencia;
import br.com.banco.repository.TransacaoRepository;
import br.com.banco.service.TransferenciaService;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {

    @Autowired
    private TransacaoRepository repository;

    @Override
    public List<Transferencia> buscarTransacoes(Long contaId, LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo,
            String nomeOperador) {
        if (inicioPeriodo != null && fimPeriodo != null && nomeOperador != null && !nomeOperador.trim().isEmpty()) {
            return transcacoesPorNomePeriodoEPorNomeOperador(nomeOperador, inicioPeriodo, fimPeriodo);
        } else {
            if (contaId != null) {
                return transacoesPorContaId(contaId);
            } else if (inicioPeriodo != null && fimPeriodo != null) {
                return transacoesPorPeriodo(inicioPeriodo, fimPeriodo);
            } else if (nomeOperador != null) {
                return transacoesPorNomeOperador(nomeOperador);
            } else {
                return todasTransacoes();
            }
        }
    }

    @Override
    public List<Transferencia> transacoesPorContaId(Long contaId) {
        return repository.findByContaId(contaId);
    }

    @Override
    public List<Transferencia> transacoesPorPeriodo(LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo) {
        return repository.findByDataTransferenciaBetween(inicioPeriodo, fimPeriodo);
    }

    @Override
    public List<Transferencia> transacoesPorNomeOperador(String nomeOperador) {
        return repository.findByNomeOperadorTransacao(nomeOperador);
    }

    @Override
    public List<Transferencia> todasTransacoes() {
        return repository.findAll();
    }

    @Override
    public List<Transferencia> transcacoesPorNomePeriodoEPorNomeOperador(String nomeOperador,
            LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo) {
        return repository.findByNomeOperadorTransacaoAndDataTransferenciaBetween(nomeOperador, inicioPeriodo,
                fimPeriodo);
    }

}

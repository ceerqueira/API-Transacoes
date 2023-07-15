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
    public List<Transferencia> TransacoesPorContaId(Long contaId) {
        return repository.findByContaId(contaId);
    }

    @Override
    public List<Transferencia> TransacoesPorPeriodo(LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo) {
        return repository.findByDataTransferenciaBetween(inicioPeriodo, fimPeriodo);
    }

    @Override
    public List<Transferencia> TransacoesPorNomeOperador(String nomeOperador) {
        return repository.findByNomeOperadorTransacao(nomeOperador);
    }

    @Override
    public List<Transferencia> TodasTransacoes() {
        return repository.findAll();
    }

    @Override
    public List<Transferencia> TranscacoesPorNomePeriodoEPorNomeOperador(String nomeOperador,
            LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo) {
        return repository.findByNomeOperadorTransacaoAndDataTransferenciaBetween(nomeOperador, inicioPeriodo,
                fimPeriodo);
    }

}

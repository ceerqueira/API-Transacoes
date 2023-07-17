package br.com.banco.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.banco.domain.Transferencia;
import br.com.banco.exception.IdContaNotFoundException;
import br.com.banco.exception.NomeNotFoundExpecion;
import br.com.banco.exception.PeriodoNotFoundExpecion;
import br.com.banco.exception.TranscacoesPorNomePeriodoEPorNomeOperadorNotFoundExpecion;
import br.com.banco.repository.TransacaoRepository;
import br.com.banco.service.TransferenciaService;

// Anotação indicando que esta classe é um serviço
@Service
public class TransferenciaServiceImpl implements TransferenciaService {

    // Injeção automática da classe TransacaoRepository
    @Autowired
    private TransacaoRepository repository;

    // Implementação do método buscarTransacoes da interface TransferenciaService
    @Override
    public List<Transferencia> buscarTransacoes(Long contaId, LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo,
            String nomeOperador) {

        // Verifica quais parâmetros foram fornecidos e chama o método correspondente
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

    // Implementação dos outros métodos da interface TransferenciaService
    // Eles buscam transações com base em diferentes critérios e lançam exceções se não encontrarem nenhuma transação
    @Override
    public List<Transferencia> transacoesPorContaId(Long contaId) {
        if (repository.findByContaId(contaId).isEmpty()) {
            throw new IdContaNotFoundException("O número do ID Conta não foi encontrado");
        }

        return repository.findByContaId(contaId);
    }

    @Override
    public List<Transferencia> transacoesPorPeriodo(LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo) {
        if (repository.findByDataTransferenciaBetween(inicioPeriodo, fimPeriodo).isEmpty()) {
            throw new PeriodoNotFoundExpecion("O Periodo de tempo escolhido não foi encontrado");
        }
        return repository.findByDataTransferenciaBetween(inicioPeriodo, fimPeriodo);
    }

    @Override
    public List<Transferencia> transacoesPorNomeOperador(String nomeOperador) {
                if (repository.findByNomeOperadorTransacao(nomeOperador).isEmpty()) {
            throw new NomeNotFoundExpecion("Nome do Operador não encontrado");
        }
        return repository.findByNomeOperadorTransacao(nomeOperador);
    }

    @Override
    public List<Transferencia> todasTransacoes() {
        return repository.findAll();
    }

    @Override
    public List<Transferencia> transcacoesPorNomePeriodoEPorNomeOperador(String nomeOperador,LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo) {
        if(repository.findByNomeOperadorTransacaoAndDataTransferenciaBetween(nomeOperador, inicioPeriodo,fimPeriodo).isEmpty()){
                throw new TranscacoesPorNomePeriodoEPorNomeOperadorNotFoundExpecion("Nome do Operador ou Periodo escolhido não encontrado");
                }
        
        return repository.findByNomeOperadorTransacaoAndDataTransferenciaBetween(nomeOperador, inicioPeriodo,
                fimPeriodo);
    }
}

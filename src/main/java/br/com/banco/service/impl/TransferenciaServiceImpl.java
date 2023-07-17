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

// A anotação @Service indica que esta classe é um serviço no Spring.
@Service
public class TransferenciaServiceImpl implements TransferenciaService {

    // Injeção de dependência do TransacaoRepository.
    @Autowired
    private TransacaoRepository repository;

    // Método para buscar transações com base nos parâmetros fornecidos.
    @Override
    public List<Transferencia> buscarTransacoes(Long contaId, LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo,
            String nomeOperador) {

        // Verifica se todos os parâmetros foram fornecidos.
        // Se foram, chama o método para buscar por operador e período.
        if (inicioPeriodo != null && fimPeriodo != null && nomeOperador != null && !nomeOperador.trim().isEmpty()) {
            return transcacoesPorNomePeriodoEPorNomeOperador(nomeOperador, inicioPeriodo, fimPeriodo);
        } else {
            // Se apenas a conta foi fornecida, busca transações daquela conta.
            if (contaId != null) {
                return transacoesPorContaId(contaId);
            }
            // Se o período foi fornecido, busca transações naquele período.
            else if (inicioPeriodo != null && fimPeriodo != null) {
                return transacoesPorPeriodo(inicioPeriodo, fimPeriodo);
            }
            // Se o nome do operador foi fornecido, busca transações daquele operador.
            else if (nomeOperador != null) {
                return transacoesPorNomeOperador(nomeOperador);
            }
            // Se nenhum parâmetro foi fornecido, retorna todas as transações.
            else {
                return todasTransacoes();
            }
        }
    }

    // Método para buscar transações por ID de conta.
    // Retorna exceção se a conta não for encontrada.
    @Override
    public List<Transferencia> transacoesPorContaId(Long contaId) {

        if (repository.findByContaId(contaId).isEmpty()) {
            throw new IdContaNotFoundException("O número do ID Conta não foi encontrado");
        }

        return repository.findByContaId(contaId);
    }

    // Método para buscar transações por período.
    // Retorna exceção se não houver transações no período.
    @Override
    public List<Transferencia> transacoesPorPeriodo(LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo) {

        if (repository.findByDataTransferenciaBetween(inicioPeriodo, fimPeriodo).isEmpty()) {
            throw new PeriodoNotFoundExpecion("O Periodo de tempo escolhido não foi encontrado");
        }

        return repository.findByDataTransferenciaBetween(inicioPeriodo, fimPeriodo);
    }

    // Método para buscar transações por nome de operador.
    // Retorna exceção se o operador não for encontrado.
    @Override
    public List<Transferencia> transacoesPorNomeOperador(String nomeOperador) {

        if (repository.findByNomeOperadorTransacao(nomeOperador).isEmpty()) {
            throw new NomeNotFoundExpecion("Nome do Operador não encontrado");
        }

        return repository.findByNomeOperadorTransacao(nomeOperador);
    }

    // Método para buscar todas as transações.
    @Override
    public List<Transferencia> todasTransacoes() {
        return repository.findAll();
    }

    // Método para buscar transações por nome de operador e período.
    // Retorna exceção se o operador ou o período não forem encontrados.
    @Override
    public List<Transferencia> transcacoesPorNomePeriodoEPorNomeOperador(String nomeOperador,
            LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo) {

        if (repository
                .findByNomeOperadorTransacaoAndDataTransferenciaBetween(nomeOperador, inicioPeriodo, fimPeriodo)
                .isEmpty()) {
            throw new TranscacoesPorNomePeriodoEPorNomeOperadorNotFoundExpecion(
                    "Nome do Operador ou Periodo escolhido não encontrado");
        }

        return repository.findByNomeOperadorTransacaoAndDataTransferenciaBetween(nomeOperador, inicioPeriodo,
                fimPeriodo);
    }

}

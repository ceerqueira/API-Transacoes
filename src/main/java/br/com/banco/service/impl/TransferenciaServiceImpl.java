package br.com.banco.service.impl;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
    public ResponseEntity<?> buscarTransacoes(Long contaId, LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo,
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
    public ResponseEntity<?> transacoesPorContaId(Long contaId) {
        try {
            if (repository.findByContaId(contaId).isEmpty()) {
                throw new IdContaNotFoundException("O número do ID Conta não foi encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.ok().body(repository.findByContaId(contaId));
    }

    // Método para buscar transações por período.
    // Retorna exceção se não houver transações no período.
    @Override
    public ResponseEntity<?> transacoesPorPeriodo(LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo) {
        try {
            if (repository.findByDataTransferenciaBetween(inicioPeriodo, fimPeriodo).isEmpty()) {
                throw new PeriodoNotFoundExpecion("O Periodo de tempo escolhido não foi encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok().body(repository.findByDataTransferenciaBetween(inicioPeriodo, fimPeriodo));
    }

    // Método para buscar transações por nome de operador.
    // Retorna exceção se o operador não for encontrado.
    @Override
    public ResponseEntity<?> transacoesPorNomeOperador(String nomeOperador) {
        try {
            if (repository.findByNomeOperadorTransacao(nomeOperador).isEmpty()) {
                throw new NomeNotFoundExpecion("Nome do Operador não encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok().body(repository.findByNomeOperadorTransacao(nomeOperador));
    }

    // Método para buscar todas as transações.
    @Override
    public ResponseEntity<?> todasTransacoes() {
        return ResponseEntity.ok(repository.findAll());
    }

    // Método para buscar transações por nome de operador e período.
    // Retorna exceção se o operador ou o período não forem encontrados.
    @Override
    public ResponseEntity<?> transcacoesPorNomePeriodoEPorNomeOperador(String nomeOperador,
            LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo) {
        try {
            if (repository
                    .findByNomeOperadorTransacaoAndDataTransferenciaBetween(nomeOperador, inicioPeriodo, fimPeriodo)
                    .isEmpty()) {
                throw new TranscacoesPorNomePeriodoEPorNomeOperadorNotFoundExpecion(
                        "Nome do Operador ou Periodo escolhido não encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity
                .ok(repository.findByNomeOperadorTransacaoAndDataTransferenciaBetween(nomeOperador, inicioPeriodo,
                        fimPeriodo));
    }
}

package br.com.banco.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.banco.domain.Transferencia;

@Repository
public interface TransacaoRepository extends JpaRepository<Transferencia, Long> {
    List<Transferencia> findByContaId(Long contaId);

    List<Transferencia> findByDataTransferenciaBetween(LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo);

    List<Transferencia> findByNomeOperadorTransacao(String nomeOperador);

    List<Transferencia> findByNomeOperadorTransacaoAndDataTransferenciaBetween(String nomeOperador, LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo);

}

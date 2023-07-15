package br.com.banco.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.banco.domain.Transferencia;
import br.com.banco.service.TransferenciaService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transferencia")
@CrossOrigin(origins = "http://localhost:3000") 
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @GetMapping
    public ResponseEntity<List<Transferencia>> Transacoes(
            @RequestParam(required = false) Long contaId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicioPeriodo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fimPeriodo,
            @RequestParam(required = false) String nomeOperador) {
        

        List<Transferencia> transacoes;

        if (inicioPeriodo != null && fimPeriodo != null &&(nomeOperador != null && !nomeOperador.trim().isEmpty())) {
            transacoes = transferenciaService.TranscacoesPorNomePeriodoEPorNomeOperador(nomeOperador, inicioPeriodo,
                    fimPeriodo);
        } else {
            if (contaId != null) {
                transacoes = transferenciaService.TransacoesPorContaId(contaId);
            } else if (inicioPeriodo != null && fimPeriodo != null) {
                transacoes = transferenciaService.TransacoesPorPeriodo(inicioPeriodo, fimPeriodo);
            } else if (nomeOperador != null) {
                transacoes = transferenciaService.TransacoesPorNomeOperador(nomeOperador);
            } else {
                transacoes = transferenciaService.TodasTransacoes();
            }
        }

        return ResponseEntity.ok(transacoes);
    }
}

// Importando as classes e anotações necessárias
package br.com.banco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.banco.domain.Transferencia;
import br.com.banco.service.TransferenciaService;

import java.time.LocalDateTime;
import java.util.List;

// Anotação indicando que esta classe é um controlador REST
@RestController
// Mapeia todas as requisições para "/transferencia"
@RequestMapping("/transferencia")
// Indica que o controlador aceita requisições cross-origin de "http://localhost:3000"
@CrossOrigin(origins = "http://localhost:3000")
public class TransferenciaController {

    // Anotação que permite a injeção automática da classe TransferenciaService
    @Autowired
    private TransferenciaService transferenciaService;

    // Mapeia as requisições GET para este método
    @GetMapping
    public ResponseEntity<?> Transacoes(
            // Parâmetros opcionais para o método. 
            // Se o parâmetro não estiver presente na solicitação, o valor padrão será nulo
            @RequestParam(required = false) Long contaId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicioPeriodo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fimPeriodo,
            @RequestParam(required = false) String nomeOperador) {

        List<Transferencia> transacoes;
        try{
            // Busca as transações com os parâmetros fornecidos
            transacoes = transferenciaService.buscarTransacoes(contaId, inicioPeriodo, fimPeriodo, nomeOperador);
        }catch (Exception e){
            // Se um erro ocorrer durante a busca, retorna o status HTTP 204 (NO_CONTENT)
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        // Se a busca for bem-sucedida, retorna as transações encontradas com status HTTP 200 (OK)
        return ResponseEntity.ok(transacoes);
    }
}

package br.com.banco;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.banco.domain.Transferencia;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BancoApplicationTests {



    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testBuscarTransacoesPorNomeOperador() {
        String nomeOperador = "Beltrano";
        String url = "http://localhost:8080"  + "/transferencia?nomeOperador=" + nomeOperador;
        ResponseEntity<Transferencia[]> responseEntity = restTemplate.getForEntity(url, Transferencia[].class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Transferencia[] transferencias = responseEntity.getBody();
        assertThat(transferencias).isNotNull();
        assertThat(transferencias.length).isGreaterThan(0);
    }

    @Test
    void testBuscarTransacoesPorPeriodo() {
        // Defina o período de tempo desejado
        LocalDateTime inicioPeriodo = LocalDateTime.of(2019, 1, 1, 0, 0, 0);
        LocalDateTime fimPeriodo = LocalDateTime.of(2022, 12, 31, 23, 59, 59);

        String url = "http://localhost:8080" + "/transferencia?inicioPeriodo=" + inicioPeriodo + "&fimPeriodo=" + fimPeriodo;
        ResponseEntity<Transferencia[]> responseEntity = restTemplate.getForEntity(url, Transferencia[].class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Transferencia[] transferencias = responseEntity.getBody();
        assertThat(transferencias).isNotNull();
    }
    
    @Test
    void testBuscarTransacoesPorContaId() {
        int contaId = 1;
        String url = "http://localhost:8080" + "/transferencia?contaId=" + contaId;
        ResponseEntity<Transferencia[]> responseEntity = restTemplate.getForEntity(url, Transferencia[].class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Transferencia[] transferencias = responseEntity.getBody();
        assertThat(transferencias).isNotNull();
        assertThat(transferencias.length).isGreaterThan(0);
    }

    @Test
    void testBuscarTodasTransacoes() {
        String url = "http://localhost:8080" + "/transferencia";
        ResponseEntity<Transferencia[]> responseEntity = restTemplate.getForEntity(url, Transferencia[].class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Transferencia[] transferencias = responseEntity.getBody();
        assertThat(transferencias).isNotNull();
        assertThat(transferencias.length).isGreaterThan(0);
    }

}

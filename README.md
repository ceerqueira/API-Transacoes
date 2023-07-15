# API Transações 

## Descrição 

A API Transações é uma solução RESTful escrita em Java, projetada para consulta de transações por meio de diferentes parâmetros: ID da conta, período de tempo ou nome do operador. Sua estrutura robusta e escalável proporciona uma maneira eficiente de gerenciar e acessar informações de transações.

## Pré-requisitos
Antes de iniciar, certifique-se de ter o seguinte software instalado:

- Java JDK 11 ou superior
- Maven 3.6.3 ou superior

## Como executar a aplicação 

### IDE

A aplicação pode ser executada através de qualquer IDE de sua preferência que suporte o desenvolvimento em Java e Maven. Basta importar o projeto e executar a classe principal.

### Linha de Comando

Se você preferir executar a aplicação através da linha de comando, siga os passos abaixo:

1. Primeiro, compile o projeto utilizando o Maven Wrapper. No terminal, navegue até a pasta do projeto e execute o comando de acordo com o seu sistema operacional:

    - Para sistemas Linux/macOS:

        ```shell
        ./mvnw clean package
        ```

    - Para sistemas Windows:

        ```shell
        .\mvnw.cmd clean package
        ```

2. Após o build bem-sucedido, um arquivo JAR será gerado no diretório `target`. Para executar este arquivo, utilize o comando:

    ```shell
    java -jar target/<nomeDoArquivoGerado>.jar
    ```

Substitua `<nomeDoArquivoGerado>.jar` pelo nome do arquivo JAR gerado no passo anterior.

## Documentação da API 

A documentação da API está disponível através do Swagger UI, que pode ser acessado em `http://localhost:8080/swagger-ui.html` após a aplicação ser iniciada.

Abaixo, algumas capturas de tela da interface do Swagger UI:

<img width="1201" alt="Captura de Tela 2023-07-15 às 12 00 06" src="https://github.com/ceerqueira/API-Transacoes/assets/50030996/b1ae7930-cfbe-44fe-b264-f89254da9644">


<img width="1152" alt="Captura de Tela 2023-07-15 às 12 00 49" src="https://github.com/ceerqueira/API-Transacoes/assets/50030996/a57ad923-bfc1-46eb-8a2d-9a65272cbfcf">

<img width="1145" alt="Captura de Tela 2023-07-15 às 12 00 58" src="https://github.com/ceerqueira/API-Transacoes/assets/50030996/d7c46cc4-174c-4027-803f-5a4420ce5bdc">

<img width="1153" alt="Captura de Tela 2023-07-15 às 12 00 31" src="https://github.com/ceerqueira/API-Transacoes/assets/50030996/31b19961-cc99-4b92-973a-12ce403afa7d">



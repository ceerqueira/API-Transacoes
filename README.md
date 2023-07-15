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

![Captura de Tela 1](https://github.com/ceerqueira/API-Transacoes/assets/50030996/a04161db-ad7b-44ad-9b47-2a2bc8e5c169.png)

![Captura de Tela 2](https://github.com/ceerqueira/API-Transacoes/assets/50030996/d3e4a071-2bc4-4d0f-80d4-32344b390b1c.png)

![Captura de Tela 3](https://github.com/ceerqueira/API-Transacoes/assets/50030996/4e25a5c3-2d5d-463b-a946-7ac391dd3972.png)



# SpendWise - Controle Financeiro

## Descrição

Aplicação desenvolvida em Spring Boot visando fornecer uma API para o aplicativo desenvolvido em Flutter 
na matéria de Projeto Integrador VI.

## Funcionalidades

- Cadastro/Exclusão de transações feitas pelo usuário (Débito/Crédito);
- Balanço geral de todas as transações cadastradas;
- Dados para geração de gráficos, agregando os valores com suas respectivas categorias;
- Criação de grupos, bem como a adição e remoção de integrantes;
- Adição/Exclusão de transações no grupo;

## Tecnologias Utilizadas

- `Java 17`
- `Spring Boot`
- `Spring Data JPA`
- `Flyway`
- `MySql`
- `Bean Validation`
- `Lombok`
- `Spring Security`
- `OAuth2 Resource Server`
- `Keycloak Admin Client`

## Instalação

Há duas possibilidades para a execução deste projeto, sendo elas:

* Clonar o repositório e executar o código via IDE ou então gerar um arquivo ".jar" e executar via linha de comando;
* Utilizar container para subir a aplicação;

Tanto para o primeiro quanto para o segundo cenário será necessário fornecer algumas configurações para que a aplicação
possa ser executada sem problemas, sendo que elas devem ser feitas no arquivo **<sub>1</sub> application.properites** no primeiro caso e
para o segundo por variáveis de ambiente no arquivo **<sub>2</sub> compose.yml**.

* Banco de Dados:
  * `URL do banco`
    1. *spring.datasource.url*
    2. *DATABASE_URL*

  * `Usuário para acesso`
    1. *spring.datasource.username*
    2. *DATABASE_USERNAME*
  
  * `Senha do usuário`
    1. *spring.datasource.password*
    2. *DATABASE_PASS*

  * `Driver do banco`
    1. *spring.datasource.driver-class-name*
    2. *Caso deseje utilizar outro banco será necessário modificar a aplicação e definir corretamente as variáveis acima*

Já em relação ao Keycloak deve-se salientar que a URL utilizada para realizar o login deve ser a mesma utilizada pela
aplicação backend para validação. Já que divergências no campo `iss` do `access token` gerado podem fazer com que a requisição não seja
autorizada.

* Servidor de autenticação (KeyCloak):
  * `URL para o realm`
    1. *spring.security.oauth2.resourceserver.jwt.issuer-uri*
    2. *ISSUER_URI*

  * `URL para API do realm`
    1. *spring.security.oauth2.resourceserver.jwt.jwk-set-uri*
    2. *JWK_URI*


* KeyCloak Admin Client:
  * `URL do KeyCloak`
    1. *spring.keycloak.application.url*
    2. *KEYCLOAK_URL*
  
  * `KeyCloak Realm (Default master)`
    1. *spring.keycloak.application.realm*
  
  * `KeyCloak Client (Default admin-cli)`
    1. *spring.keycloak.application.client*

  * `Usuário do KeyCloak`
    1. *spring.keycloak.application.username*
    2. *KEYCLOAK_ADMIN_USER*
  
  * `Senha do Usuário`
    1. *spring.keycloak.application.password*
    2. *KEYCLOAK_ADMIN_PASSWORD*

## Licença

GNU GENERAL PUBLIC LICENSE v3
# api-starwars-testes

Projeto criado para treinar testes no Spring Framework, houve a utilização de SonarLint para detecção de erros, mas ressalto que a prioridade deste projeto é a prática do JUnit 5. 

## Features

- Testes Unitários;
- Testes de Integração;
- Testes Subcutâneos;
- Relatório de Cobertura com JaCoCo;
- Testes de Mutantes PiTest.

## Tech

Foram utilizados:

- Docker;
- Java: V11;
- JUnit: V5;
- Maven: V3.8.7
- MySQL: V8;
- Spring-Boot: V2.3.7-RELEASE.

## Comandos
##
Executa todos os testes
```sh
mvn clean verify
```
##
Executa **SOMENTE** testes de integração
```sh
mvn clean verify -Dsurefire.skip=true
```
##
Executa **SOMENTE** testes unitários
```sh
mvn clean verify -DskipITs=true
```
##
Executa testes unitários e em seguida gera relatórios de cobertura, em: **target\site\jacoco\index.html**
```sh
mvn clean install
mvn test jacoco:report
```
##
Executa testes unitários e em seguida gera relatórios de cobertura, em: **target\pit-reports\{DIRETORIO_GERADO}\index.html**
```sh
mvn test-compile org.pitest:pitest-maven:mutationCoverage
```
##

Descrição de **applications-properties**:
- application-properties: Definir o application a ser utilizado;
- application-dev.properties: Utilizado para o desenvolvimento da aplicação com MySQL8. Neste caso deve ser preenchido com variáveis de ambiente;
- application-test.properties: Utilizado para testes unitários e integração;
- application-subcutaneo.properties: Utilizado para testes subcutâneos. Os testes ocorrem em container.

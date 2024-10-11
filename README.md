# Microsserviço de Logística

## Descrição

O Microsserviço de Logística faz parte do sistema de gerenciamento de pedidos baseado em arquitetura de microsserviços utilizando o ecossistema Spring. Este serviço é responsável por todas as operações relacionadas à entrega de pedidos, desde a atribuição de entregadores até o rastreamento das entregas em tempo real.

Ele também calcula as rotas mais eficientes, estima os tempos de entrega e fornece atualizações de status para os clientes, garantindo que o processo de entrega seja otimizado e que os clientes estejam sempre informados sobre o status de suas entregas.

## Funcionalidades

- **Atribuição de Entregadores**: Automatiza o processo de atribuição de entregadores com base na disponibilidade e localização.
- **Rastreamento em Tempo Real**: Atualiza o status das entregas em tempo real, permitindo que os clientes acompanhem seus pedidos.
- **Cálculo de Rotas**: Determina as rotas mais eficientes para entrega, otimizando tempo e recursos.
- **Estimativa de Tempo de Entrega**: Calcula o tempo estimado para entrega e atualiza conforme o andamento da rota.
- **Persistência de Dados**: Utiliza Spring Data JPA para armazenar e gerenciar as informações de entregas no banco de dados.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para desenvolvimento e configuração do microsserviço.
- **Spring Data JPA**: Facilita o gerenciamento de dados no banco de dados relacional.
- **Spring Cloud Stream**: Comunicação baseada em eventos para receber atualizações de outros microsserviços.
  
## Como Executar o Projeto

### Pré-requisitos

- Java 17+
- Docker (opcional, para uso de banco de dados e contêineres)

### Passos

1. Clone o repositório:

   ```bash
   git clone git@github.com:WaldirJuniorGPN/ms-logistica.git
   ```

2. Navegue até o diretório do projeto:

   ```bash
   cd ms-logistica
   ```

3. Execute o projeto com o Maven:

   ```bash
   mvn spring-boot:run
   ```

4. Para utilizar Docker, inicie o banco de dados MySQL e configure conforme as variáveis de ambiente necessárias.

## Testes

O microsserviço possui testes automatizados para garantir a correta implementação das funcionalidades.

### Executar os Testes

```bash
mvn test
```


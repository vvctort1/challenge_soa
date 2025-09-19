# Second Nature

O **Second Nature** é uma aplicação web desenvolvida em Spring Boot que funciona como uma plataforma de monitoramento e acompanhamento do bem-estar mental e emocional de usuários participantes de apostas. O sistema permite que indivíduos registrem e acompanhem seu estado emocional, humor e níveis de impulsividade através de check-ins diários, além de monitorar padrões de acesso à plataforma. A aplicação foi construída utilizando Spring Data JPA para persistência no MySQL e Flyway para controle de versão do banco de dados.

O projeto oferece funcionalidades de gestão de usuários com cadastro, atualização e exclusão, incluindo validações como email único e campos obrigatórios. O sistema de controle de acessos registra logins do usuário na plataforma, gerando dados para análise de frequência de uso. A funcionalidade de check-ins diários de humor, permite aos usuários descrever seu estado emocional e classificar seus níveis de impulsividade em três categorias: controlado, moderado e alto. O sistema garante a integridade dos dados limitando um check-in por dia por usuário e mantém um histórico para acompanhamento da evolução emocional ao longo do tempo.

A arquitetura técnica utiliza Java 17+ como linguagem principal, com Spring Boot 3.x fornecendo o framework base para desenvolvimento da API REST. A persistência de dados é gerenciada através do Spring Data JPA e Hibernate, conectando-se a um banco MySQL para armazenamento estruturado das informações. O Flyway controla as migrações do banco de dados, garantindo versionamento adequado das mudanças estruturais. Para aumentar a produtividade do desenvolvimento, foram integradas bibliotecas como Lombok para redução de código boilerplate, Jakarta Bean Validation para validações declarativas e Jackson para processamento JSON automático.

Todas as APIs seguem o padrão RESTful com endpoints intuitivos, implementando paginação nativa para otimização de performance em listagens extensas. O sistema inclui validações que previnem múltiplos check-ins no mesmo dia, verificam a ativação de usuários antes de registros e possui tratamento de erros e exceções. Os dados são organizados através do padrão DTO (Data Transfer Objects) utilizando Records do Java, garantindo transferência segura entre as camadas da aplicação. O projeto é uma solução moderna para ajudar usuários a enfrentar seus impulsos em apostas com uma ferramenta digital pensada na saúde mental e conscientização.
<br><br>
## Passo a passo
1. Clonar o projeto;
2. Configurar os dados de conexão com o MySql no arquivo application.properties;
3. Abrir o MySql Workbench e criar o banco de dados;
4. Rodar o projeto;
5. Realizar os testes utilizando comandos sql ou plataformas como Postman.
<br><br>
## ENDPOINTS USUARIO

##### Criar usuários
POST http://localhost:8080/usuario

##### Listar todos os usuários
GET http://localhost:8080/usuario

##### Mostrar usuário específico
GET http://localhost:8080/usuario/{id_usuario}

##### Alterar dados do usuário
PUT http://localhost:8080/usuario

##### Deletar usuário
DELETE http://localhost:8080/usuario/{id_usuario}

<br><br>
## ENDPOINTS ACESSO

##### Criar acessos
POST http://localhost:8080/acesso

##### Listar todos os acessos
GET http://localhost:8080/acesso

##### Listar acessos por usuário específico
GET http://localhost:8080/acesso/usuario/{id_usuario}

<br><br>
## ENDPOINTS CHECKIN

##### Criar checkins
POST http://localhost:8080/checkin

##### Listar todos os checkins
GET http://localhost:8080/checkin

##### Listar checkins por usuário específico  
GET http://localhost:8080/checkin/usuario/{id_usuario}

#### Buscar checkin de hoje de um usuário específico
GET http://localhost:8080/checkin/hoje/{id_usuario}

<br><br>

## ADICIONANDO DADOS NAS TABELAS
##### USUARIO FICTICIO
{
    "nome": "{{$randomFirstName}} {{$randomLastName}}",
    "email": "{{$randomEmail}}",
    "senha": "11111111"
}

##### ACESSO FICTICIOS
{
    "id_usuario": 1
}

##### CHECKIN FICTICIO
{
    "id_usuario": 1,
    "humor": "ansioso",
    "impulsividade_nivel": "ALTO"
}

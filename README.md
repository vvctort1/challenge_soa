# Second Nature

<p align='justify'>&nbsp;&nbsp;&nbsp;&nbsp;O Second Nature é uma aplicação web desenvolvida em Spring Boot que funciona como uma plataforma de monitoramento e acompanhamento do bem-estar mental e emocional de usuários participantes de apostas. O sistema permite que indivíduos registrem e acompanhem seu estado emocional, humor e níveis de impulsividade através de check-ins diários, além de monitorar padrões de acesso à plataforma. A aplicação foi construída utilizando Spring Data JPA para persistência no MySQL e Flyway para controle de versão do banco de dados.</p>

<p align='justify'>&nbsp;&nbsp;&nbsp;&nbsp;O projeto oferece funcionalidades de gestão de usuários com cadastro, atualização e exclusão, incluindo validações como email único e campos obrigatórios. O sistema de controle de acessos registra logins do usuário na plataforma, gerando dados para análise de frequência de uso. A funcionalidade de check-ins diários de humor, permite aos usuários descrever seu estado emocional e classificar seus níveis de impulsividade em três categorias: controlado, moderado e alto. O sistema garante a integridade dos dados limitando um check-in por dia por usuário e mantém um histórico para acompanhamento da evolução emocional ao longo do tempo.</p>

<p align='justify'>&nbsp;&nbsp;&nbsp;&nbsp;A arquitetura técnica utiliza Java 17+ como linguagem principal, com Spring Boot 3.x fornecendo o framework base para desenvolvimento da API REST. A persistência de dados é gerenciada através do Spring Data JPA e Hibernate, conectando-se a um banco MySQL para armazenamento estruturado das informações. O Flyway controla as migrações do banco de dados, garantindo versionamento adequado das mudanças estruturais. Para aumentar a produtividade do desenvolvimento, foram integradas bibliotecas como Lombok para redução de código boilerplate, Jakarta Bean Validation para validações declarativas e Jackson para processamento JSON automático.</p>

<p align='justify'>&nbsp;&nbsp;&nbsp;&nbsp;Todas as APIs seguem o padrão RESTful com endpoints intuitivos, implementando paginação nativa para otimização de performance em listagens extensas. O sistema inclui validações que previnem múltiplos check-ins no mesmo dia, verificam a ativação de usuários antes de registros e possui tratamento de erros e exceções. Os dados são organizados através do padrão DTO (Data Transfer Objects) utilizando Records do Java, garantindo transferência segura entre as camadas da aplicação. O projeto é uma solução moderna para ajudar usuários a enfrentar seus impulsos em apostas com uma ferramenta digital pensada na saúde mental e conscientização.</p>

---

## Passo a passo

1. Clonar o projeto;
2. Configurar os dados de conexão com o MySql no arquivo application.properties;
3. Abrir o MySql Workbench e criar o banco de dados;
4. Rodar o projeto;
5. Realizar os testes utilizando comandos sql ou plataformas como Postman.

---

## ENDPOINTS USUARIO

### Criar usuários
POST http://localhost:8080/usuario

<img width="1092" height="550" alt="image" src="https://github.com/user-attachments/assets/980ccf17-10b4-42ab-b16d-455f3c811a94" />

---

### Listar todos os usuários
GET http://localhost:8080/usuario

<img width="1115" height="917" alt="image" src="https://github.com/user-attachments/assets/26612c36-70e6-4630-9c8b-16629495bdc7" />

---

### Mostrar usuário específico
GET http://localhost:8080/usuario/{id_usuario}

<img width="1087" height="550" alt="image" src="https://github.com/user-attachments/assets/90fd2f73-d8a4-4ace-ba0b-faea45a6818d" />

---

### Alterar dados do usuário
PUT http://localhost:8080/usuario/{id_usuario}

<img width="1104" height="517" alt="image" src="https://github.com/user-attachments/assets/5e017b25-31d1-49c9-b6be-6a53de1962bc" />

---

### Deletar usuário
DELETE http://localhost:8080/usuario/{id_usuario}

<img width="1107" height="463" alt="image" src="https://github.com/user-attachments/assets/3fec6f18-4657-46f9-a08e-ad4258c6ebb4" />

---

## ENDPOINTS ACESSO

### Criar acessos
POST http://localhost:8080/acesso/usuario/{id_usuario}

<img width="1112" height="519" alt="image" src="https://github.com/user-attachments/assets/e9a7e87f-596a-428e-ab11-e82c5f7e81ef" />

---

### Listar todos os acessos
GET http://localhost:8080/acesso

---

### Listar acessos por usuário específico
GET http://localhost:8080/acesso/usuario/{id_usuario}

<img width="1106" height="517" alt="image" src="https://github.com/user-attachments/assets/98bbb743-e193-41de-8dbf-762d4989074e" />

---

## ENDPOINTS CHECKIN

### Criar checkins
POST http://localhost:8080/checkin

<img width="1105" height="457" alt="image" src="https://github.com/user-attachments/assets/08c0e875-01d5-404b-beb4-1bf6a005d69e" />

---

### Listar todos os checkins
GET http://localhost:8080/checkin

<img width="1110" height="808" alt="image" src="https://github.com/user-attachments/assets/b2be004e-6f15-439a-85a6-7f9b393287dd" />

---

### Listar checkins por usuário específico  
GET http://localhost:8080/checkin/usuario/{id_usuario}

<img width="1113" height="809" alt="image" src="https://github.com/user-attachments/assets/d3c456d8-f2eb-4a0c-bc40-0d16ed7aab13" />

---

### Buscar checkin de hoje de um usuário específico
GET http://localhost:8080/checkin/hoje/{id_usuario}

---

## ADICIONANDO DADOS NAS TABELAS
##### USUARIO FICTICIO
```json
{
    "nome": "{{$randomFirstName}} {{$randomLastName}}",
    "email": "{{$randomEmail}}",
    "senha": "11111111"
}
```
##### ACESSO FICTICIOS
```json
{
    "id_usuario": 1
}
```
##### CHECKIN FICTICIO
```json
{
    "id_usuario": 1,
    "humor": "ansioso",
    "impulsividade_nivel": "ALTO"
}
```

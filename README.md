<h1 align='center'>
Second Nature
</h1>

<p align='justify'>&nbsp;&nbsp;&nbsp;&nbsp;O Second Nature é uma aplicação web desenvolvida em Spring Boot que funciona como uma plataforma de monitoramento e acompanhamento do bem-estar mental e emocional de usuários participantes de apostas. O sistema permite que indivíduos registrem e acompanhem seu estado emocional, humor e níveis de impulsividade através de check-ins diários, além de monitorar padrões de acesso à plataforma. A aplicação foi construída utilizando Spring Data JPA para persistência no MySQL e Flyway para controle de versão do banco de dados.</p>

<p align='justify'>&nbsp;&nbsp;&nbsp;&nbsp;O projeto oferece funcionalidades de gestão de usuários com cadastro, atualização e exclusão, incluindo validações como email único e campos obrigatórios. O sistema de controle de acessos registra logins do usuário na plataforma, gerando dados para análise de frequência de uso. A funcionalidade de check-ins diários de humor, permite aos usuários descrever seu estado emocional e classificar seus níveis de impulsividade em três categorias: controlado, moderado e alto. O sistema garante a integridade dos dados limitando um check-in por dia por usuário e mantém um histórico para acompanhamento da evolução emocional ao longo do tempo.</p>

<p align='justify'>&nbsp;&nbsp;&nbsp;&nbsp;A arquitetura técnica utiliza Java 17+ como linguagem principal, com Spring Boot 3.x fornecendo o framework base para desenvolvimento da API REST. A persistência de dados é gerenciada através do Spring Data JPA e Hibernate, conectando-se a um banco MySQL para armazenamento estruturado das informações. O Flyway controla as migrações do banco de dados, garantindo versionamento adequado das mudanças estruturais. Para aumentar a produtividade do desenvolvimento, foram integradas bibliotecas como Lombok para redução de código boilerplate, Jakarta Bean Validation para validações declarativas e Jackson para processamento JSON automático.</p>

<p align='justify'>&nbsp;&nbsp;&nbsp;&nbsp;Todas as APIs seguem o padrão RESTful com endpoints intuitivos, implementando paginação nativa para otimização de performance em listagens extensas. O sistema inclui validações que previnem múltiplos check-ins no mesmo dia, verificam a ativação de usuários antes de registros e possui tratamento de erros e exceções. Os dados são organizados através do padrão DTO (Data Transfer Objects) utilizando Records do Java, garantindo transferência segura entre as camadas da aplicação. O projeto é uma solução moderna para ajudar usuários a enfrentar seus impulsos em apostas com uma ferramenta digital pensada na saúde mental e conscientização.</p>

---

<br>

# Passo a passo

1. Clonar o projeto;
2. Configurar os dados de conexão com o MySql no arquivo application.properties;
3. Abrir o MySql Workbench e criar o banco de dados;
4. Rodar o projeto;
5. Abrir no navegador (http://localhost:8080/swagger-ui.html);
6. Utiliza o método post do usuário para criar usuário com suas credenciais (guardar e-mail e senha);
7. Inserir as credenciais no método post de autenticação;
8. Copiar o token JWT no response body no método post da autenticação;
9. Inserir o token JWT ao clicar no botão "Authorize" no início da página
10. Confirmar no botão interno "Authorize";
11. Agora é possível realizar todos os testes.

---
<br>

<h1 align="center">
Swagger
</h1>

<p align="center">
<img width="600" height="600" alt="image swagger" src="https://github.com/user-attachments/assets/12a312c7-866c-4e8a-8ae6-7caa65cd9da5" /> 
</p>

<p align="center">
<img width="600" height="600" alt="image swagger" src="https://github.com/user-attachments/assets/fe873d3a-1bbb-4de4-a274-c9758c6c921c" />
</p>



---

<br>

<h1 align='center'>
Diagramas
</h1>

<h3 align="center">
Entidade-Relacionamento
</h3>
<p align='center'>
<img width="600" height="600" alt="image" src="https://github.com/user-attachments/assets/8da8ebd3-8e6e-4db3-80f0-eda810ce16d8" />
</p>

<h3 align='center'>
Arquitetura
</h3>
<p align='center'>
<img width="954" height="341" alt="image" src="https://github.com/user-attachments/assets/6043e81a-aacc-48ff-8a5d-f19b86358f8b" />
</p>


## Integrantes

| Nome | RM | Turma |
| :--- | :--- | :--- |
| Arthur Baldissera Claumann Marcos | 550219 | 3ESPF |
| Gabriel Genaro Dalaqua | 551986 | 3ESPF |
| Paloma Mirela dos Santos Rodrigues | 551321 | 3ESPF |
| Ricardo Ramos Vergani | 550166 | 3ESPF |
| Victor Kenzo Toma | 551649 | 3ESPF |

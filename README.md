
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

## ENDPOINTS ACESSO

##### Criar acessos
POST http://localhost:8080/acesso

##### Listar todos os acessos
GET http://localhost:8080/acesso

##### Listar acessos por usuário específico
GET http://localhost:8080/acesso/usuario/{id_usuario}


## ENDPOINTS CHECKIN

##### Criar checkins
POST http://localhost:8080/checkin

##### Listar todos os checkins
GET http://localhost:8080/checkin

##### Listar checkins por usuário específico  
GET http://localhost:8080/checkin/usuario/{id_usuario}

### Buscar checkin de hoje de um usuário específico
GET http://localhost:8080/checkin/hoje/{id_usuario}



## ADICIONANDO DADOS NAS TABELAS DO DATABASE "second_nature"
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

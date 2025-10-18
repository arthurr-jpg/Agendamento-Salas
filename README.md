# Agenda de Reuniões - API REST

Esta aplicação é uma **API REST** desenvolvida em **Java 17** com **Spring Boot**, que permite gerenciar reservas de salas de reunião da empresa.  


---

## Requisitos

- Agendar uma sala (criar reserva)
- Editar um agendamento existente
- Visualizar reservas de uma sala
- Deletar reservas
- Visualizar reservas por usuário, período ou todas (opcional para extensões)

---

## Tecnologias

- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 Database (em memória)
- Jakarta Validation
- Maven

---

## Como Executar

1. Clone o projeto:

```bash
git clone https://github.com/seu-usuario/agenda-reunioes.git
cd agenda-reunioes
```
2. Compile e execute a aplicação usando Maven:

```
mvn clean install
mvn spring-boot:run
```
3. A aplicação estará rodando em:
http://localhost:8084/api
```
```
4. Acesse o console do H2 (para verificar dados salvos):
```
http://localhost:8084/h2-console
JDBC URL: jdbc:h2:mem:agendas
```
Usuário: sa

Senha: (vazio)

## Endpoints
### 1. Criar uma reserva

URL: /api/reservas

### Método: POST

Request Body (JSON):

{
  "sala": "Sala01",
  "usuario": "Joao",
  "inicio": "2025-10-20T09:00:00",
  "fim": "2025-10-20T10:00:00"
}

 Response 201 (Created):
{
  "id": 1,
  "sala": "Sala01",
  "usuario": "Joao",
  "inicio": "2025-10-20T09:00:00",
  "fim": "2025-10-20T10:00:00"
}

### 2. Editar uma reserva

URL: /api/reservas/{id}

### Método: PUT

Request Body (JSON):
{
  "sala": "Sala01",
  "usuario": "Joao",
  "inicio": "2025-10-20T09:30:00",
  "fim": "2025-10-20T10:30:00"
}

 Response 200 (OK):
{
  "id": 1,
  "sala": "Sala01",
  "usuario": "Joao",
  "inicio": "2025-10-20T09:30:00",
  "fim": "2025-10-20T10:30:00"
}

 Response 404 (Not Found):

{
  "error": "Reserva não encontrada"
}

### 3. Listar reservas de uma sala

URL: /api/salas/{nome}/reservas

### Método: GET

Response 200 (OK):

[
  {
    "id": 1,
    "sala": "Sala01",
    "usuario": "Joao",
    "inicio": "2025-10-20T09:30:00",
    "fim": "2025-10-20T10:30:00"
  },

  
  {
    "id": 2,
    "sala": "Sala01",
    "usuario": "Maria",
    "inicio": "2025-10-20T11:00:00",
    "fim": "2025-10-20T12:00:00"
  }
]


### 4. Deletar uma reserva

URL: /api/reservas/{id}

### Método: DELETE

Response 204 (No Content)

Response 404 (Not Found)

{
  "error": "Reserva não encontrada"
}

### 5. Listar todas as reservas (opcional)

URL: /api/reservas

### Método: GET

Response 200 (OK):

[
  {  
    "id": 1,
    "sala": "Sala01",
    "usuario": "Joao",
    "inicio": "2025-10-20T09:30:00",
    "fim": "2025-10-20T10:30:00"
  },
  ...
]

### 6. Listar reservas por usuário (opcional)

URL: /api/usuarios/{usuario}/reservas

### Método: GET

Response 200 (OK):

[
  {
    "id": 1,
    "sala": "Sala01",
    "usuario": "Joao",
    "inicio": "2025-10-20T09:30:00",
    "fim": "2025-10-20T10:30:00"
  }
]


### 7. Listar reservas por período (opcional)

URL: /api/reservas/periodo?inicio={ISO_DATE}&fim={ISO_DATE}

### Método: GET

Exemplo: /api/reservas/periodo?inicio=2025-10-20T00:00:00&fim=2025-10-21T00:00:00

Response 200 (OK):


[
  {
    "id": 1,
    "sala": "Sala01",
    "usuario": "Joao",
    "inicio": "2025-10-20T09:30:00",
    "fim": "2025-10-20T10:30:00"
  }
]


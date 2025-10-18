# Agenda de Reuniões - API REST

Esta aplicação é uma **API REST** desenvolvida em **Java 17** com **Spring Boot**, que permite gerenciar reservas de salas de reunião da empresa.  
Para fins de simplicidade, o usuário é considerado “logado” através de um parâmetro enviado nas requisições (não há autenticação).

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

2. Compile e execute a aplicação usando Maven:

```bash
mvn clean install
mvn spring-boot:run

3. A aplicação estará rodando em:
http://localhost:8084/api

4. Acesse o console do H2 (para verificar dados salvos):

http://localhost:8084/h2-console
JDBC URL: jdbc:h2:mem:agendas

Usuário: sa
Senha: (vazio)

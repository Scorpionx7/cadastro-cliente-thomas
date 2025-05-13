
# 📦 Cliente API - Desafio Técnico Thomas Greg

Este projeto é uma API RESTful desenvolvida em Java com Spring Boot para gerenciamento de clientes e seus logradouros. Atende aos requisitos de autenticação, performance e uso de Stored Procedures.

---

## 📐 Arquitetura da Solução

![Arquitetura da Solução](https://github.com/user-attachments/assets/a73f6b53-0a71-407b-b9bd-b55b544527b5)

---

## 🔧 Tecnologias Utilizadas

- Java 8+
- Spring Boot
- Spring Data JPA
- Spring Security + JWT
- SQL Server
- Hibernate
- Postman (testes)
- JSF + PrimeFaces (frontend)
- Maven

---

## 🚀 Como Executar o Projeto

### 1. Clone o repositório

```bash
git clone https://github.com/Scorpionx7/cadastro-cliente-thomas
cd cliente-api
```

### 2. Configure o `application.properties`

```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=clientes_db;encrypt=true;trustServerCertificate=true
spring.datasource.username=sa
spring.datasource.password=832285
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLServer2016Dialect
```

### 3. Crie o banco de dados e execute as Stored Procedures

No SQL Server Management Studio (SSMS), execute o script:

```sql
CREATE DATABASE clientes_db;
```

Depois rode o arquivo:

```
stored_procedures.sql
```

---

## 🔐 Autenticação e Autorização

A API usa **JWT** com dois perfis de usuário:

- `ADMIN`: pode criar, editar, deletar
- `USER`: pode apenas visualizar

### Endpoints abertos:
- `POST /auth/register`
- `POST /auth/login`

### Endpoints protegidos:
- `GET /clientes`, `POST /clientes`, etc.
- Requer header:
  ```
  Authorization: Bearer {seu_token_aqui}
  ```

---

## ✨ Funcionalidades

### 📁 Cliente
- Criar cliente com: nome, email (único), upload de logotipo
- Buscar cliente 
- Excluir cliente
- Atualizar cliente
- Listar todos os clientes
- Visualizar e excluir cliente por ID
- Visualizar logotipo: `GET /clientes/{id}/logotipo`

### 📍 Logradouro
- Criar logradouro vinculado a um cliente existente
- Atualizar endereço de um logradouro por ID
- Remover logradouro (com desviculação do cliente, se necessário)
- Buscar logradouro por ID
- Listar todos os logradouros registrados

### 📊 Relatórios (via Stored Procedure)
- `GET /relatorios/clientes-multilogradouros`
- `GET /relatorios/total-logradouros`
- `GET /relatorios/dominios-mais-usados`
- `GET /relatorios/logradouros-duplicados`

---

## 🗂️ Estrutura de Pacotes

```
├── config/           → Configurações de segurança e JWT
├── controller/       → Endpoints da API (REST)
├── dto/              → Objetos de transferência de dados
├── entity/           → Entidades JPA (banco de dados)
├── exception/        → Tratamento de erros customizados
├── repository/       → Interfaces Spring Data JPA
├── service/          → Lógica de negócio e autenticação
└── resources/        → Configurações do projeto

```

---

## ✅ Testes com Postman

Exemplos:

- `POST /auth/login`
- `POST /clientes` (multipart/form-data)
- `GET /clientes?page=0&size=10`

---

## 📁 Arquivo de Procedures

O script `stored_procedures.sql` contém:

- `sp_clientes_com_qtd_logradouros`
- `sp_dominio_email_mais_usado`
- `sp_logradouros_duplicados`

Execute no SSMS antes de iniciar a API.

---

## ✍️ Autora

Desenvolvido por **Esther**  
Java Backend Developer  
[LinkedIn](https://www.linkedin.com/in/estherrezende/)
📧 Email: rezendealvesesther@gmail.com

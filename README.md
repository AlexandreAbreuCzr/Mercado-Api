# Mercado-API 🛒 | Spring Boot

API REST desenvolvida em **Spring Boot** com foco em estudo de **backend**, **arquitetura MVC**, **autenticação com JWT** e **controle de acesso por nível de usuário**.

---

## 🎯 Objetivo do Projeto
Este projeto tem como objetivo consolidar conceitos de:
- Spring Boot
- APIs REST
- Arquitetura MVC
- DTOs
- Autenticação e autorização com JWT
- Persistência de dados com JPA/Hibernate

---

## ⚙️ Tecnologias Utilizadas
- Java 17+
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT
- Banco de dados H2 / PostgreSQL
- Maven

---

## 🧩 Funcionalidades

### 👤 Usuários
- Cadastro de usuários
- Autenticação via login
- Geração de token JWT
- Controle de acesso por nível de responsabilidade (roles)

### 📦 Produtos
- Cadastro de produtos
- Listagem de produtos
- Atualização de produtos
- Remoção de produtos
- Acesso controlado por permissão

---

## 🔐 Segurança
- Autenticação baseada em JWT
- API stateless
- Endpoints protegidos por permissões

---

## ▶️ Como Executar o Projeto

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/Mercado-API.git
```

2. Entre no diretório:
```bash
cd Mercado-API
```

3. Execute a aplicação:
```bash
mvn spring-boot:run
```

4. A API estará disponível em:
```bash
http://localhost:8080
```
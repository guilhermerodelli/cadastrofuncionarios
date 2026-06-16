# Sistema de Cadastro de Funcionários

Aplicação web para gestão de funcionários e lançamentos financeiros, com exportação de relatórios em PDF.

## Demo e links

| Recurso | URL |
|---------|-----|
| **Portfólio (Vercel)** | https://cadastrofuncionarios-ruby.vercel.app |
| **Demo ao vivo (Render)** | https://cadastrofuncionarios.onrender.com |
| **Repositório** | https://github.com/guilhermerodelli/cadastrofuncionarios |

> A demo no Render usa plano gratuito: após ~15 min de inatividade, a primeira carga pode levar até 60 segundos.

## Funcionalidades

- CRUD completo de funcionários (nome, CPF, RG, PIS, CTPS, salário, etc.)
- Lançamentos financeiros por funcionário
- Geração de relatório PDF dos lançamentos
- Busca de funcionários

## Stack

- Java 17 · Spring Boot 3.5 · Spring Data JPA · Thymeleaf
- PostgreSQL · Bootstrap 5 · iText PDF · Docker

## Rodar localmente

### Pré-requisitos

- Java 17
- PostgreSQL rodando localmente
- Banco `cadastroclientes` criado

### Passos

```powershell
cd cadastroclientes
.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=local
```

Acesse: http://localhost:8080

Credenciais locais padrão em `application-local.properties` (não versionado).

## Deploy

### Backend (Render)

1. Crie um banco no [Neon](https://console.neon.tech)
2. No [Render](https://dashboard.render.com), use **New → Blueprint** com o `render.yaml` do repo
3. Configure as variáveis de ambiente:
   - `SPRING_PROFILES_ACTIVE` = `prod`
   - `SPRING_DATASOURCE_URL` = `jdbc:postgresql://...?sslmode=require`
   - `SPRING_DATASOURCE_USERNAME`
   - `SPRING_DATASOURCE_PASSWORD`

### Portfólio (Vercel)

A pasta `portfolio/` contém a landing page estática. Deploy com Root Directory = `portfolio`.

## Texto sugerido para LinkedIn

> Desenvolvi um sistema de cadastro de funcionários com Spring Boot, PostgreSQL e geração de relatórios PDF. Demo: https://cadastrofuncionarios-ruby.vercel.app | Código: https://github.com/guilhermerodelli/cadastrofuncionarios

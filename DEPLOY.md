# Passo a passo: Neon + Render

## 1. Banco PostgreSQL no Neon (grátis)

1. Acesse https://console.neon.tech e crie uma conta
2. **New Project** → nome: `cadastrofuncionarios`
3. Na dashboard, copie a **Connection string** (modo JDBC ou URI)
4. Converta para formato Spring Boot:
   ```
   jdbc:postgresql://ep-xxxx.region.aws.neon.tech/neondb?sslmode=require
   ```
5. Anote **username** e **password**

## 2. Backend no Render (grátis)

1. Acesse https://dashboard.render.com
2. **New → Blueprint**
3. Conecte o repo: `guilhermerodelli/cadastrofuncionarios`
4. O Render detectará o `render.yaml`
5. Preencha as variáveis sensíveis quando solicitado:
   | Variável | Valor |
   |----------|-------|
   | `SPRING_DATASOURCE_URL` | URL JDBC do Neon com `?sslmode=require` |
   | `SPRING_DATASOURCE_USERNAME` | usuário Neon |
   | `SPRING_DATASOURCE_PASSWORD` | senha Neon |
6. Aguarde o build Docker (~5–10 min na primeira vez)
7. URL final: `https://cadastrofuncionarios.onrender.com`

## 3. Verificar

- Abra `https://cadastrofuncionarios.onrender.com/actuator/health` → deve retornar `{"status":"UP"}`
- Abra `https://cadastrofuncionarios.onrender.com/funcionarios` → tela do sistema

## 4. Portfólio na Vercel

Deploy da pasta `portfolio/` com **Root Directory** = `portfolio` no projeto Vercel.

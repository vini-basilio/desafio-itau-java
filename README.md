# 🧮 API de Transações

API REST para gerenciamento de transações financeiras com estatísticas calculadas em tempo real.

Desenvolvido para o **Desafio Técnico Itaú Unibanco**.

---

## 🚀 Tecnologias Utilizadas

- Java 21  
- Spring Boot 3.2.3  
- Maven  
- Swagger/OpenAPI  
- SLF4J (logging)  

---

## 📌 Requisitos do Desafio

- Armazenamento em memória (sem banco de dados ou cache externo)  
- API REST seguindo o padrão especificado  
- Respostas e payloads estritamente em JSON  
- Endpoints exatos: `/transacao`, `/estatisticas`  
- Estrutura de commits clara (mínimo 3, um por endpoint)  
- Código claro, organizado e testável  

---

## 📬 Endpoints

### 🔹 POST `/transacao`  
Registra uma nova transação.

**Requisição:**
```json
{
  "valor": 123.45,
  "dataHora": "2024-03-20T14:30:00.000-03:00"
}
```

**Regras:**
- `valor` deve ser ≥ 0
- `dataHora` não pode estar no futuro
- Ambos os campos são obrigatórios

**Respostas:**
- `201 Created` – Transação aceita
- `422 Unprocessable Entity` – Transação inválida
- `400 Bad Request` – JSON inválido

---

### 🔹 GET `/estatisticas`  
Retorna estatísticas das transações feitas nos últimos 60 segundos (ou período configurado).

**Parâmetro opcional:**
- `segundos`: quantidade de segundos para o cálculo (padrão: `60`)

**Resposta:**
```json
{
  "count": 10,
  "sum": 1234.56,
  "avg": 123.45,
  "min": 12.34,
  "max": 234.56
}
```

---

### 🔹 DELETE `/transacao`  
Remove todas as transações registradas.

**Resposta:**
- `200 OK` – Histórico apagado com sucesso

---

## 📈 Regras de Negócio

- Transações com valor negativo ou data futura são rejeitadas  
- As estatísticas consideram apenas transações dentro da janela configurada (padrão 60 segundos)  

---

## 📚 Documentação da API

Swagger disponível em:

```
http://localhost:8080/swagger-ui.html
```

---

## ▶️ Como Executar

1. Clone o repositório  
2. Compile e execute com Maven:
   ```bash
   mvn spring-boot:run
   ```
3. Acesse a API em:
   ```
   http://localhost:8080
   ```

---

## 🪵 Logs

A aplicação utiliza SLF4J. Logs são gerados para:
- Transações recebidas
- Validações
- Estatísticas retornadas
- Limpeza do histórico

---

## ✨ Extras Implementados (ou sugeridos)

- ✅ Swagger para documentação interativa  
- ✅ Tratamento de erros com `@ControllerAdvice` (customização opcional)
- ✅ Configuração do tempo da janela via parâmetro de query  
- ✅ Implementação de logs com SLF4J para monitoramento e debugging





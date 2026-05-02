# API de Gestão de Eventos (Atividade 03 - Desenvolvimento Web II)

Projeto desenvolvido para a disciplina Desenvolvimento Web II, focando em Spring Boot, Spring Data JPA, Spring Security e validações de API.

## Justificativa do Domínio
O domínio de "Eventos" foi escolhido por representar um cenário real e complexo de relações N-N (Muitos para Muitos - Eventos e Participantes), 1-N (Evento e Ingressos) e N-1 (Eventos e Organizadores). Isso permite explorar a Persistência Híbrida e as restrições de negócio exigidas na atividade.

## Matriz de Permissões (Roles e Endpoints)

A segurança da API foi configurada utilizando 3 roles (`MASTER`, `CONTRIBUTOR`, `AUDITOR`) e Spring Security, com autenticação via Basic Auth. 

Os usuários em memória para teste são:
- `master` / `senha123` (Role: MASTER)
- `contributor` / `senha123` (Role: CONTRIBUTOR)
- `auditor` / `senha123` (Role: AUDITOR)

| Endpoint | Método HTTP | Nível de Restrição | Roles Permitidas |
| --- | --- | --- | --- |
| `/eventos/info` | GET | Público | *Qualquer usuário sem autenticação* |
| `/eventos` | GET | Restrito 1 | MASTER, CONTRIBUTOR, AUDITOR |
| `/eventos/{id}` | GET | Restrito 1 | MASTER, CONTRIBUTOR, AUDITOR |
| `/eventos/{id}` | PUT | Restrito 2 | MASTER, CONTRIBUTOR |
| `/eventos` | POST | Restrito 3 | MASTER |
| `/eventos/{id}`| DELETE | Restrito 3 | MASTER |
| `/participantes` | POST / GET / ADD | Restrito Diversos | MASTER (POST/ADD), MASTER, CONTRIBUTOR (GET) |

## DTOs x Entidades (Desacoplamento e Validações)

As rotas da API foram configuradas para não devolver a entidade diretamente do banco de dados, protegendo informações sensíveis. Além disso, foram incluídas anotações de validação (`@NotBlank` e `@Size`).

### Exemplo: Criação de um Evento (Entrada - EventoRequestDTO)
Aqui nós passamos o ID do organizador e validamos os campos, garantindo que o nome tenha pelo menos 3 caracteres.
```json
{
  "nome": "Show de Rock",
  "local": "Arena das Dunas",
  "dataEvento": "2024-12-10T20:00:00",
  "capacidade": 5000,
  "organizadorId": 1
}
```

### Exemplo: Listagem de Evento (Saída - EventoResponseDTO)
Observe que a saída formata o evento sem exibir os metadados do banco ou relacionamentos perigosos que causariam loops infinitos.
```json
{
  "id": 1,
  "nome": "Show de Rock",
  "local": "Arena das Dunas",
  "dataEvento": "2024-12-10T20:00:00",
  "capacidade": 5000,
  "organizador": null,
  "participantes": null
}
```

## Como Executar
1. Certifique-se de ter Java 21+ instalado.
2. Rode `./mvnw spring-boot:run`
3. Importe a collection do Postman para testar as rotas utilizando Basic Auth com as credenciais listadas acima.

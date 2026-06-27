# Cenário 3 – Sistema de Escola de Cursos Livres

## Tabelas Identificadas

### Tabela: `aluno`
| Campo    | Tipo         | Restrições   |
|----------|--------------|--------------|
| id       | SERIAL       | PRIMARY KEY  |
| nome     | VARCHAR(100) | NOT NULL     |
| email    | VARCHAR(150) | NOT NULL     |
| telefone | VARCHAR(20)  | NOT NULL     |

### Tabela: `curso`
| Campo              | Tipo         | Restrições   |
|--------------------|--------------|--------------|
| id                 | SERIAL       | PRIMARY KEY  |
| nome               | VARCHAR(100) | NOT NULL     |
| descricao          | TEXT         |              |
| carga_horaria      | INTEGER      | NOT NULL     |
| vagas_totais       | INTEGER      | NOT NULL     |
| vagas_disponiveis  | INTEGER      | NOT NULL     |

### Tabela: `matricula`
| Campo          | Tipo    | Restrições                          |
|----------------|---------|-------------------------------------|
| id             | SERIAL  | PRIMARY KEY                         |
| id_aluno       | INTEGER | NOT NULL, FOREIGN KEY → aluno(id)   |
| id_curso       | INTEGER | NOT NULL, FOREIGN KEY → curso(id)   |
| data_matricula | DATE    | NOT NULL                            |
| valor          | NUMERIC(10,2) | NOT NULL                      |

---

## Comandos SQL (CREATE TABLE)

```sql
CREATE TABLE aluno (
    id        SERIAL PRIMARY KEY,
    nome      VARCHAR(100) NOT NULL,
    email     VARCHAR(150) NOT NULL,
    telefone  VARCHAR(20)  NOT NULL
);

CREATE TABLE curso (
    id                 SERIAL PRIMARY KEY,
    nome               VARCHAR(100) NOT NULL,
    descricao          TEXT,
    carga_horaria      INTEGER      NOT NULL,
    vagas_totais       INTEGER      NOT NULL,
    vagas_disponiveis  INTEGER      NOT NULL
);

CREATE TABLE matricula (
    id              SERIAL PRIMARY KEY,
    id_aluno        INTEGER        NOT NULL REFERENCES aluno(id),
    id_curso        INTEGER        NOT NULL REFERENCES curso(id),
    data_matricula  DATE           NOT NULL,
    valor           NUMERIC(10,2)  NOT NULL
);
```

---

## Regras de Negócio

1. **Um aluno deve estar cadastrado** antes de ser matriculado em qualquer curso.
2. **Um curso deve estar cadastrado** antes de receber matrículas.
3. **Não é permitido matricular um aluno em um curso que já atingiu o limite de vagas** (`vagas_disponiveis == 0`).
4. **Não é permitido matricular o mesmo aluno duas vezes no mesmo curso** (duplicidade bloqueada).
5. **O valor pago na matrícula não pode ser negativo** (valor < 0 é inválido).
6. A cada matrícula realizada com sucesso, o campo `vagas_disponiveis` do curso deve ser **decrementado em 1**.
7. Deve ser possível **consultar todos os alunos matriculados em um curso**.
8. Deve ser possível **consultar todos os cursos em que um aluno está matriculado**.

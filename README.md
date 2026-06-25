# Sistema de Oficina Mecânica

## Tabelas

### Cliente

| Campo | Tipo |
|---------|---------|
| id | SERIAL |
| nome | VARCHAR(100) |
| telefone | VARCHAR(20) |

### Veículo

| Campo | Tipo |
|---------|---------|
| id | SERIAL |
| placa | VARCHAR(10) |
| modelo | VARCHAR(100) |
| ano | INTEGER |
| id_cliente | INTEGER |

### Ordem de Serviço

| Campo | Tipo |
|---------|---------|
| id | SERIAL |
| id_veiculo | INTEGER |
| descricao | TEXT |
| valor | NUMERIC(10,2) |
| status | VARCHAR(20) |


## Regras de Negócio

1. Um cliente pode possuir vários veículos.
2. Cada veículo pertence a apenas um cliente.
3. Uma ordem de serviço só pode ser aberta para um veículo cadastrado.
4. Não permitir abrir ordem de serviço para veículo inexistente.
5. O valor do serviço não pode ser negativo.
6. O sistema deve permitir consultar o histórico de manutenções de um veículo.
7. O status da ordem de serviço deve ser aberta ou concluida
8. O veículo depende de um cliente previamente cadastrado.
9. A ordem de serviço depende de um veículo previamente cadastrado.
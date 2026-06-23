# Cenário 1 - Clínica Veterinária

## Tabelas

Tutor: id, nome, endereco, telefone

Animal: id, nome, especie, raca, id_tutor (FK para tutor)

Consulta: id, id_animal (FK para animal), data_atendimento, motivo, valor

## SQL

CREATE TABLE tutor (
id SERIAL PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
endereco VARCHAR(200),
telefone VARCHAR(20) NOT NULL
);

CREATE TABLE animal (
id SERIAL PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
especie VARCHAR(50) NOT NULL,
raca VARCHAR(50),
id_tutor INTEGER NOT NULL,
FOREIGN KEY (id_tutor) REFERENCES tutor(id)
);

CREATE TABLE consulta (
id SERIAL PRIMARY KEY,
id_animal INTEGER NOT NULL,
data_atendimento DATE NOT NULL,
motivo VARCHAR(200) NOT NULL,
valor NUMERIC(10,2) NOT NULL CHECK (valor >= 0),
FOREIGN KEY (id_animal) REFERENCES animal(id)
);

## Regras de negócio

- Um tutor pode ter mais de um animal
- O animal só pode ser cadastrado se o tutor já existir
- Não pode registrar consulta para animal que não existe
- O valor da consulta não pode ser negativo
- Tem que poder ver o histórico de consultas de um animal
- Tem que poder ver todos os animais de um tutor
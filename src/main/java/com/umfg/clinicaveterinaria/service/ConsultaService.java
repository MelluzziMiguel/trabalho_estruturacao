package com.umfg.clinicaveterinaria.service;

import com.umfg.clinicaveterinaria.model.Animal;
import com.umfg.clinicaveterinaria.model.Consulta;
import com.umfg.clinicaveterinaria.repository.AnimalRepository;
import com.umfg.clinicaveterinaria.repository.ConsultaRepository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ConsultaService {

    private final ConsultaRepository consultaRepository = new ConsultaRepository();
    private final AnimalRepository animalRepository = new AnimalRepository();

    public Consulta registrarConsulta(int idAnimal, LocalDate data, String motivo, BigDecimal valor) throws SQLException {
        Animal animal = animalRepository.buscarPorId(idAnimal);
        if (animal == null) {
            throw new IllegalArgumentException("Não é possível registrar a consulta: animal com id " + idAnimal + " não encontrado.");
        }

        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor da consulta não pode ser negativo.");
        }

        if (motivo == null || motivo.isBlank()) {
            throw new IllegalArgumentException("O motivo do atendimento é obrigatório.");
        }

        Consulta consulta = new Consulta(idAnimal, data, motivo, valor);
        return consultaRepository.salvar(consulta);
    }

    public Consulta buscarPorId(int id) throws SQLException {
        return consultaRepository.buscarPorId(id);
    }

    public List<Consulta> listarTodas() throws SQLException {
        return consultaRepository.listarTodas();
    }

    public List<Consulta> listarHistoricoPorAnimal(int idAnimal) throws SQLException {
        return consultaRepository.listarPorAnimal(idAnimal);
    }

    public boolean atualizar(Consulta consulta) throws SQLException {
        return consultaRepository.atualizar(consulta);
    }

    public boolean excluir(int id) throws SQLException {
        return consultaRepository.excluir(id);
    }
}
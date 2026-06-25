package com.umfg.clinicaveterinaria.service;

import com.umfg.clinicaveterinaria.model.Animal;
import com.umfg.clinicaveterinaria.model.Consulta;
import com.umfg.clinicaveterinaria.repository.AnimalRepository;
import com.umfg.clinicaveterinaria.repository.ConsultaRepository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final AnimalRepository animalRepository;

    public ConsultaService(ConsultaRepository consultaRepository, AnimalRepository animalRepository) {
        this.consultaRepository = consultaRepository;
        this.animalRepository = animalRepository;
    }

    public Consulta registrarConsulta(Consulta consulta) throws SQLException {
        Animal animal = animalRepository.buscarPorId(consulta.getIdAnimal());
        if (animal == null) {
            throw new IllegalArgumentException("Animal com id " + consulta.getIdAnimal() + " não encontrado.");
        }

        if (consulta.getValor() == null || consulta.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor da consulta não pode ser negativo.");
        }

        if (consulta.getMotivo() == null || consulta.getMotivo().isBlank()) {
            throw new IllegalArgumentException("O motivo do atendimento é obrigatório.");
        }

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